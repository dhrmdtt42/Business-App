package com.gstmadeeasy.Utils;

/**
 * Created by Dharam on 4/12/2018.
 */

public class MyUtil {

    public static String USERNAME = "UserName";
    public static String PASSWORD = "Password";

    public static String CALLING_ACTIVITY = "Calling_Activity";
    public static String ACTIVITY_BUSINESS_LIST = "BusinessList";

    public static String OPERATION = "Operation";
    public static String OPERATION_NEW_BUSINESS = "NewBusiness";
    public static String OPERATION_EDIT_BUSINESS = "EditBusiness";

    public static String lowerCase(String str) {
        if (str == null) {
            return "";
        }
        return str.toLowerCase();
    }

    public static int StringLength(String s) {
        if(s == null) {
            return 0;
        }
        return s.length();
    }

}
