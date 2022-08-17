package com.gold.charpter04;

import java.util.Optional;

public class Q3 {
    public static void main(String[] args) {
        Optional<String> sample = Optional.of("A");
        System.out.println(sample.orElse("B")); // A
    }
}
