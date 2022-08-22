package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Q28 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("banana", "apple", "orange");
        Stream<String> stream = list.stream();
        System.out.println(stream.count());
        stream.forEach(System.out::println); // ❌ IllegalStateException
    }
}
