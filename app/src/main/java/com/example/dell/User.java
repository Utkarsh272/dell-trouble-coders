package com.example.dell;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;

public class User {
    private static boolean guest=false;
    private static String name=null;
    private static String phone=null;
    private static String state=null;
    private static String city=null;
    private static String pincode=null;
    private static String Address=null;
    private static String emailId=null;

    public static boolean getGuest(){
        return guest;
    }
    public static void setGuest(boolean g){
        guest=g;
    }
    public static String getName(){
        return name;
    }
    public static void setName(String g){
        name=g;
    }
    public static String getPhone(){
        return phone;
    }
    public static void setPhone(String g){
        phone=g;
    }
    public static String getState(){
        return state;
    }
    public static void setState(String g){
        state=g;
    }
    public static String getCity(){
        return city;
    }
    public static void setCity(String g){
        city=g;
    }
    public static String getPincode(){
        return pincode;
    }
    public static void setPincode(String g){
        pincode=g;
    }
    public static String getAddress(){
        return Address;
    }
    public static void setAddress(String g){
        Address=g;
    }
    public static String getEmailId(){
        return emailId;
    }
    public static void setEmailId(String g){
        emailId=g.split("@")[0];
    }

}
