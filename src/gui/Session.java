/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author hamou
 */
public class Session {
     public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String name ; 
    private static String email; 
    private static String passowrd ;
    private static String tel ;
    private static String image_name;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        Session.pref = pref;
    }

    public static int getId() {
        return Preferences.get("id",id);
    }

    public static void setId(int id) {
        Preferences.set("id",id);
    }

    public static String getName() {
        return Preferences.get("name",name);
    }

    public static void setName(String name) {
         Preferences.set("name",name);
    }

    public static String getEmail() {
        return Preferences.get("email",email);
    }

    public static void setEmail(String email) {
         Preferences.set("email",email);
    }

    public static String getPassowrd() {
        return Preferences.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         Preferences.set("passowrd",passowrd);
    }

    public static String getTel() {
        return Preferences.get("tel",tel);
    }

    public static void setTel(String tel) {
         Preferences.set("tel",tel);
    }
    
    public static String getImage_name() {
        return Preferences.get("image_name",image_name);
    }

    public static void setImage_name(String image_name) {
         Preferences.set("image_name",image_name);
    }
    
    
    
    
    
}
