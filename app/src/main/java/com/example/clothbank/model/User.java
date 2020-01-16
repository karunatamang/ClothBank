package com.example.clothbank.model;

public class User {
    private String _id;
    private String fullname;
    private String email;
    private  String password;
    private  String address;
    private  String phonenumber;
    private String token;



    public User(String fullname, String email, String password, String address, String phonenumber) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getToken() {
        return token;
    }
}
