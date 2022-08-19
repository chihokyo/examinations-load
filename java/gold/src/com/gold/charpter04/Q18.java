package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Q18 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // String::valueOf返回是String类型，所以这里也要返回这个
        Stream<String> res = list.stream().map(String::valueOf);
        System.out.println(res);
    }
}
