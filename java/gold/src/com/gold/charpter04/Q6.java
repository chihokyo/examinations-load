package com.gold.charpter04;

import java.util.Optional;

public class Q6 {
    public static void main(String[] args) {
        Optional<String> sample = Optional.empty();
        Optional<String> sample2 = Optional.of("hello world");
        sample.ifPresentOrElse((str) -> System.out.println(str), () -> System.out.println("null的话就执行我")); // null的话就执行我
        sample2.ifPresentOrElse((str) -> System.out.println(str), () -> System.out.println("null的话就执行我")); // 2
    }
}
