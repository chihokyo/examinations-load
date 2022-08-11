package com.gold.chapter10;

import java.util.Locale;

public class Q1 {

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale); // zh_JP_#Hans
        System.out.println(Locale.CHINA); // zh_CN
        System.out.println(Locale.JAPAN); // ja_JP
        System.out.println(Locale.US); // en_US

        System.out.println("**************");


        Locale  locale2 = Locale.US;
        System.out.println(locale2); // en_US
    }
}
