package com.gold.streamApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * stream API分为3个步骤
 * 1 创建
 * 2 中间操作
 * 3 终止操作 (必须要有这个 不然不执行)
 */
public class StreamApi01 {
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
    }
}
