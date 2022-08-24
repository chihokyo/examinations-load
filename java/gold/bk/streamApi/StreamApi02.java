package com.gold.streamApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamApi02 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Stream<Integer> stream = list.stream();
        // forEach就是终止操作，终止操作就观剧
        stream.filter(e -> e > 2).forEach(i -> System.out.println(i));
        // 所以这样直接就执行不行，需要再次生成新的
        // stream.limit(2).forEach(System.out::println);

        System.out.println("******截断*******");
        Stream<Integer> stream2 = list.stream();
        stream2.limit(2).forEach(System.out::println);
        System.out.println("******跳过*******");
        Stream<Integer> stream3 = list.stream();
        stream3.skip(2).forEach(System.out::println);

        System.out.println("******去重*******");
        list.add(3);
        list.add(8);
        list.stream().distinct().forEach(System.out::println);
    }

}
