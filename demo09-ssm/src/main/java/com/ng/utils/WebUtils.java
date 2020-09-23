package com.ng.utils;

public class WebUtils {

    public static Integer parseInt(String strInt, int defaultValue){

        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }

}
