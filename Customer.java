package com.example.myfirebase;

public class Customer {

    private String emergencycontact, fname, lname;
    private int accnum, lastsocial;

    public Customer(String emergencycontact, String fname, String lname, int accnum, int lastsocial) {
        this.emergencycontact = emergencycontact;
        this.fname = fname;
        this.lname = lname;
        this.accnum = accnum;
        this.lastsocial = lastsocial;
    }
    public Customer() {

    }

    public String getEmergencycontact() {
        return emergencycontact;
    }

    public void setEmergencycontact(String emergencycontact) {
        this.emergencycontact = emergencycontact;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAccnum() {
        return accnum;
    }

    public void setAccnum(int accnum) {
        this.accnum = accnum;
    }

    public int getLastsocial() {
        return lastsocial;
    }

    public void setLastsocial(int lastsocial) {
        this.lastsocial = lastsocial;
    }


}
