package com.gstmadeeasy.Model;

/**
 * Created by Dharam on 12/26/2017.
 */

public class User {
    private static  String business;
    private static  String contacts;
    private static  String emails;
    private static  String address;
    private static  int MobileNo;
    private static  int status;


    public User(String business, String contacts, String emails, String address, int status,int MobileNo ){
        this.business = business;
        this.contacts = contacts;
        this.emails = emails;
        this.address = address;
        this.status = status;
        this.MobileNo = MobileNo;



    }

    public static int getMobileNo() {
        return MobileNo;
    }

    public static void setMobileNo(int mobileNo) {
        MobileNo = mobileNo;
    }





    public static String getBusiness() {
        return business;
    }

    public static void setBusiness(String business) {
        User.business = business;
    }

    public static String getContacts() {
        return contacts;
    }

    public static void setContacts(String contacts) {
        User.contacts = contacts;
    }

    public static String getEmails() {
        return emails;
    }

    public static void setEmails(String emails) {
        User.emails = emails;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        User.address = address;
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        User.status = status;
    }



}
