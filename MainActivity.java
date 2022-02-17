package com.example.myfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reff;
    Customer customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Firebase Connection Successful", Toast.LENGTH_LONG).show();
    }
    public void btn_add_rec(View v)
    {
        String emergencycontact, fname, lname, key;
        int accnum, lastsocial;
        EditText ed1, ed2, ed3, ed4, ed5;
        ed1 = findViewById(R.id.ed_accn);
        ed2 = findViewById(R.id.ed_fname);
        ed3 = findViewById(R.id.ed_lname);
        ed4 = findViewById(R.id.ed_social);
        ed5 = findViewById(R.id.ed_emcontact);

        emergencycontact = ed5.getText().toString();
        fname = ed2.getText().toString();
        lname = ed3.getText().toString();
        accnum = Integer.parseInt(ed1.getText().toString());
        lastsocial = Integer.parseInt(ed4.getText().toString());

        customer = new Customer(emergencycontact, fname, lname, accnum, lastsocial);
        customer.setAccnum(accnum);
        customer.setEmergencycontact(emergencycontact);
        customer.setLastsocial(lastsocial);
        customer.setFname(fname);
        customer.setLname(lname);
        key = lname + fname;
        reff = FirebaseDatabase.getInstance().getReference().child("Customer");
        reff.push().setValue(customer);
        reff.child(key).setValue(customer);
        Toast.makeText(MainActivity.this,"Inserted Record",Toast.LENGTH_LONG).show();
    }


    public void btn_del(View v)
    {
        EditText ed_key;
        String key;

        ed_key = (EditText)findViewById(R.id.ed_multi1);
        key = ed_key.getText().toString();

        reff = FirebaseDatabase.getInstance().getReference().child("Customer").child(key);
        reff.removeValue();

        Toast.makeText(MainActivity.this, "Removed Record",Toast.LENGTH_LONG).show();
    }


    public void btn_search1(View v) {
        EditText ed_accn, ed_fname, ed_lname, ed_social, ed_emcontact, ed_key;
        String key;

        ed_accn = (EditText) findViewById(R.id.ed_accn);
        ed_fname = (EditText) findViewById(R.id.ed_fname);
        ed_lname = (EditText) findViewById(R.id.ed_lname);
        ed_social = (EditText) findViewById(R.id.ed_social);
        ed_emcontact = (EditText) findViewById(R.id.ed_emcontact);
        ed_key = (EditText) findViewById(R.id.ed_multi1);
        key = ed_key.getText().toString();
        reff = FirebaseDatabase.getInstance().getReference().child("Customer").child(key);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String accn = snapshot.child("accnum").getValue().toString();
                    String fname = snapshot.child("fname").getValue().toString();
                    String lname = snapshot.child("lname").getValue().toString();
                    String social = snapshot.child("lastsocial").getValue().toString();
                    String emcontact = snapshot.child("emergencycontact").getValue().toString();
                    ed_accn.setText(accn);
                    ed_fname.setText(fname);
                    ed_lname.setText(lname);
                    ed_social.setText(social);
                    ed_emcontact.setText(emcontact);
                } else
                    Toast.makeText(MainActivity.this, "Record Not Found", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
        public void btn_multi(View v)
            {
                reff = FirebaseDatabase.getInstance().getReference().child("Customer");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        double average, sum, agen;
                        int count = 0;
                        String accnum;
                        sum = 0;
                        if (snapshot .hasChildren())
                        {
                            for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                accnum = dataSnapshot1.child("accnum").getValue().toString();
                                agen = Double.parseDouble(accnum);
                                sum = sum + agen;
                                count = count + 1;
                            }

                        }
                        average = sum / count;
                        Toast.makeText(MainActivity.this,String.valueOf(average),Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

            });

    }}
