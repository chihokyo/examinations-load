package com.gold.charpter04;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Q23 {
    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> res = list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        res.forEach(System.out::println);
    }
}
