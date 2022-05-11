package com.app.auth.util;

public class AppUtil {

   public static long generateOtp(){
        return Math.round(Math.random()*1000000);
    }
}
