package com.nick.pm.utils.password;

import java.util.Random;

public class PasswordGenetator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static String getRandomLetter(String lowerUpper){
        String ch = null;
        StringBuilder builder = new StringBuilder(lowerUpper);
        Random random = new Random();
        int i = random.nextInt(builder.length());
        ch = String.valueOf(builder.charAt(i));
        return ch;
    }

    public static String getRandomDigit(){
        String ch = null;
        Random random = new Random();
        ch = String.valueOf(random.nextInt(10));
        return ch;
    }

    public static String genPass(int size){
        String pass = "";
        for (int i=0;i<size;i++){
            Random random = new Random();
            int n = random.nextInt(3);
            if (n==0){
                pass =pass + getRandomLetter(LOWER);
            }
            if (n==1){
                pass = pass + getRandomLetter(UPPER);
            }
            if (n==2){
                pass = pass + getRandomDigit();
            }
        }
        return pass;
    }
}
