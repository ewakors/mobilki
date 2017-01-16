package com.example.us.imagedb;


public class User {
    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";

    // property help us to keep data
    public int user_id;
    public String password,name,surname;
    public String email;


    public String getUserEmail() {

        return email;
    }

    public void setUserEmail(String email) {

        this.email = email;
    }
    public String getUserName() {

        return name;
    }

    public void setUserName(String name) {

        this.name = name;
    }

    public String getUserSurnme() {

        return surname;
    }

    public void setUserSurname(String surname) {

        this.surname = surname;
    }

    public String getUserPassword() {

        return password;
    }

    public void setUserPassword(String password) {

        this.password = password;
    }
}
