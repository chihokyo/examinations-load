package com.gold.charpter04;

import java.util.Optional;

public class Q5 {
    public static void main(String[] args) {

        Optional<String> sample = Optional.of("test");
        sample.ifPresent((str) -> System.out.println(str)); // test

        // 只要有值才能发生，如果没有值，压根不会报错。下面的代码也压根不会执行
        Optional<String> sampleNull = Optional.empty();
        sampleNull.ifPresent((str) -> System.out.println(str));

    }
}
