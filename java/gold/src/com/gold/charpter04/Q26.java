package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Q26 {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("banana", "apple", "orange");
        Stream<String> stream = list.stream();
        Set<Boolean> booleans = stream.collect(Collectors.partitioningBy(str -> str.length() > 5)).keySet();
        booleans.forEach(System.out::println); // false,true

    }
}


