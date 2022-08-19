package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;

public class Q14 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.stream().filter(x -> x > 5).forEach(System.out::println);
    }
}
