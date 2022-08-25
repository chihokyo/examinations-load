package com.gold.charpterfinal;

import java.util.List;
import java.util.Optional;

public class Q21 {
    public static void main(String[] args) {
        var list = List.of("apple", "banana", "orange", "melon");
        Optional<String> res = list.stream().filter(x -> x.contains("x")).reduce((i, j) -> i + "," + j);
        res.ifPresent(System.out::println); // 没有符合条件的 就什么都不会发生
    }
}
