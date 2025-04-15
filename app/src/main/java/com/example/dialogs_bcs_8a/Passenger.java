package com.example.dialogs_bcs_8a;

public class Passenger {
    String name;
    String phone;
    String pref;

    public Passenger(String name, String phone, String pref) {
        this.name = name;
        this.phone = phone;
        this.pref = pref;
    }

    public Passenger() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }
}
