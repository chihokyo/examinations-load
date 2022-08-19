package com.gold.charpter04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Q11 {
    public static void main(String[] args) {
        // 方式1创建方法 通过集合
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        arrayList.stream(); // 1 创建一个顺序流（按照顺序来）
        arrayList.parallelStream(); // 1 创建一个并行流

        // 方式2 通过数组，调用 Arrays 的静态方法 stream
        int[] arr = new int[]{1, 2, 3};
        IntStream stream = Arrays.stream(arr);

        // 方式3 Stream的方法
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);

        // 方式4 创建无限流,0开始，会0,2,4,5...
        Stream.iterate(0, t -> t + 2); // 因为是无限的
    }
}
