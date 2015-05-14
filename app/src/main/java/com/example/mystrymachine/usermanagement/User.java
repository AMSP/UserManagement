package com.example.mystrymachine.usermanagement;

import java.sql.Date;

/**
 * Created by MYSTRY MACHINE on 4/8/2015.
 */
public class User {
    String user_id;
    String user_name;
    String user_gender;
    String user_bDate;
    String user_mNumber;



    public User(String user_id,String user_name, String user_gender, String user_bDate, String user_mNumber) {
        this.user_id=user_id;
        this.user_name = user_name;
        this.user_gender = user_gender;
        this.user_bDate = user_bDate;
        this.user_mNumber = user_mNumber;
    }

    public User() {

    }

    public User(String uname, String uGender, String uBDate, String uMNumber) {
        this.user_name = uname;
        this.user_gender = uGender;
        this.user_bDate = uBDate;
        this.user_mNumber = uMNumber;

    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_bDate() {
        return user_bDate;
    }

    public void setUser_bDate(String user_bDate) {
        this.user_bDate = user_bDate;
    }

    public String getUser_mNumber() {
        return user_mNumber;
    }

    public void setUser_mNumber(String user_mNumber) {
        this.user_mNumber = user_mNumber;
    }


}
