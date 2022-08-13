package com.gold.chapter02;

import java.util.function.BiFunction;

public class Q11 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, String> test = (a, b) -> Integer.toString(a + b);
        System.out.println(test.apply(10, 6)); // 16
    }
}
