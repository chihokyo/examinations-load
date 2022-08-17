package com.gold.charpter04;

import java.util.Locale;
import java.util.Optional;

public class Q7 {
    public static void main(String[] args) {
        Optional<String> sample = Optional.of("abcde");
        Optional<String> result = sample.map(str -> str.toUpperCase());

        System.out.println(sample.get()); // abcde
        System.out.println(result.get()); // ABCDE
    }
}
