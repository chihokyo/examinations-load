package com.gold.charpter04;

import java.util.Optional;

public class Q4 {
    public static void main(String[] args) throws Exception {
        Optional<String> sample = Optional.empty();
        System.out.println(sample.orElseThrow(() -> new Exception()));
    }
}
