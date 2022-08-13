package com.gold.chapter02;

import java.util.function.Function;

public class Q10 {
    public static void main(String[] args) {
        Function<Integer, Integer> a = x -> x + 2;
        Function<Integer, Integer> b = x -> x * 2;

        System.out.println(a.compose(b).apply(10)); // 22 → 10 * 2 + 2
        System.out.println(a.andThen(b).apply(10)); // 24 → (10 + 2) * 2

    }
}
