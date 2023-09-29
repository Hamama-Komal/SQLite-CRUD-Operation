package com.example.sqlitedatabase;

public class ModelClass {

    private String id,name, fname, email, contact;

    public ModelClass(String id,String name,String fname, String email, String contact) {
        this.id = id;
        this.name = name;
        this.fname = fname;
        this.email = email;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getFname() {
        return fname;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }
}
