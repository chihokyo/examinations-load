package com.gold.charpter04;

import java.util.List;

public class Q9 {
    public static void main(String[] args) {

        // 不可变的
        List<Integer> integers = List.of(1, 2, 3);
        // integers.add(6); ❌ // ImmutableCollections

        List.of(1,2,3).forEach(x -> System.out.println(x)); // 遍历
        List.of(1,2,3).forEach(System.out::println); // 另一种写法
    }
}
