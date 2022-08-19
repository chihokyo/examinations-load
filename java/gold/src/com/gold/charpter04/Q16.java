package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Q16 {
    public static void main(String[] args) {
        // 顺序流
        String[] array = {"A", "B", "C"};
        Stream<String> stream = Arrays.stream(array);
        Optional<String> res1 = stream.findAny();
        res1.ifPresent(System.out::println); // A


        String[] array2 = {"A", "B", "C"};
        Stream<String> stream2 = Arrays.stream(array2);
        Optional<String> res2 = stream2.findFirst();
        res2.ifPresent(System.out::println); // A

        // 并行流
        List<Integer> integers = List.of(1, 2, 3);
        integers.parallelStream().findAny().ifPresent(System.out::println);

        List<Integer> integers2 = List.of(1, 2, 3);
        integers2.parallelStream().findFirst().ifPresent(System.out::println);


    }
}
