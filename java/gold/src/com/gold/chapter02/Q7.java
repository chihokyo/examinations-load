package com.gold.chapter02;

import java.util.function.Predicate;

public class Q7 {
    public static void main(String[] args) {
        Predicate<Integer> p1 = x -> x > 0;
        Predicate<Integer> p2 = x -> x < 0;
        // 原本写法
        System.out.println(p1.test(100) || p2.test(100)); // true
        // 可以简写为
        System.out.println(p1.or(p2).test(100));
    }
}
