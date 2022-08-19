package com.gold.charpter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Q13 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A", "B", "C");
        List<String> list2 = Arrays.asList("A", "B", "C");

        Stream<String> stream = list.stream(); // 顺序流
        Stream<String> stream1 = list2.parallelStream();// 并行流
    }
}
