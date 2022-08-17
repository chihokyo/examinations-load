package com.gold.charpter04;

import java.util.Optional;

public class Q2 {
    public static void main(String[] args) {
        Optional<String> sample = Optional.ofNullable(null);
        System.out.println(sample.get()); // NoSuchElementException
    }
}
