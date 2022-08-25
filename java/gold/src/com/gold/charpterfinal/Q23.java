package com.gold.charpterfinal;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Q23 {
    public static void main(String[] args) {
        int array[][] = {{1, 2}, {3, 4}, {5, 6}};
        long count = Stream.of(array)
                .flatMapToInt(IntStream::of)
                .map(n -> n * 2)
                // 相当于debug输出到控制台
                .peek(System.out::println)
                .filter(n -> n % 3 == 0)
                .count();
        System.out.println(count);
    }
}
