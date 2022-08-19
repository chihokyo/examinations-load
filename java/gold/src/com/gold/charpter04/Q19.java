package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;

public class Q19 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // reduce第一个参数是初始值，初始值是啥类型，返回值就是啥类型
        Integer res = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(res); // 15
    }
}
