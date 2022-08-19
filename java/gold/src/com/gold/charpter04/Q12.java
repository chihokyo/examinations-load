package com.gold.charpter04;

import java.util.*;
import java.util.stream.Stream;

public class Q12 {
    public static void main(String[] args) {
        // 按照顺序
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        Stream<String> stream = list.stream();
        stream.forEach(x -> System.out.println(x)); // A B C D E

        // 按照特性
        Set<String> set = new HashSet<>();
        set.add("E");
        set.add("D");
        set.add("C");
        set.add("B");
        set.add("A");
        Stream<String> stream2 = set.stream();
        stream2.forEach(System.out::println); // A B C D E
    }
}
