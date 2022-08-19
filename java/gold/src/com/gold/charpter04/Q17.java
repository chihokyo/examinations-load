package com.gold.charpter04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Q17 {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(4, 5, 3, 2, 1);
        integers.stream().sorted((a, b) -> {
            if (a < b) return -1;
            if (b < a) return 1;
            return 0;
        }).forEach(System.out::println);

        // 和题目无关，这一题可以直接转换成
        List<Integer> integers2 = Arrays.asList(4, 5, 3, 2, 1);
        integers2.stream().sorted((a, b) -> {
            return a - b;
        }).forEach(System.out::println);

        // 进一步进化
        List<Integer> integers3 = Arrays.asList(4, 5, 3, 2, 1);
        integers3.stream().sorted(Comparator.comparingInt(a -> a)).forEach(System.out::println);

    }
}
