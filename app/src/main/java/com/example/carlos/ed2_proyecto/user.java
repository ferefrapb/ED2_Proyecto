package com.example.carlos.ed2_proyecto;

import com.google.gson.annotations.SerializedName;

public class user {
    public user(String name, String userName, String password, String email, String lastName) {
        Name = name;
        UserName = userName;
        Password = password;
        Email = email;
        LastName = lastName;
    }

    private String Name;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
    @SerializedName("Username")
    private String UserName;
    private String Password;
    private String Email;
    private String LastName;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

}

