package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Q21 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("B,", "A", "C", "D");
        Optional<String> res = list.stream().max(String::compareTo);
        res.ifPresent(System.out::println); // D
    }
}
