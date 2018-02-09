package com.nick.pm.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public static String stringToLowerCase(String s){
        return s.toLowerCase();
    }

    public static String firstLetterToHighCase(String s){
        String str = stringToLowerCase(s);
        Character firstChar = str.charAt(0);
        Character firstCapitalChar = Character.toUpperCase(firstChar);
        return firstCapitalChar + str.substring(1);
    }
}
