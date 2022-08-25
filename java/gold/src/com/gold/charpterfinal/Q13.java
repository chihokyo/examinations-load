package com.gold.charpterfinal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Q13 {
    public static void main(String[] args) {
        Locale l = new Locale("en", "US");
        LocalDate today = LocalDate.of(2021, 4, 1);
        String mToday = today.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(l)
        );

        String sToday = today.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(l)
        );
        System.out.println(mToday); // Apr 1, 2021
        System.out.println(sToday); // 4/1/21
    }
}
