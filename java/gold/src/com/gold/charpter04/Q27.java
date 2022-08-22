package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class Q27 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("banana", "apple", "orange");
        Stream<String> stream = list.stream();
        stream.filter(str -> str.length() > 5)
                // 这是一个debug方法，会输出到控制台
                .peek(str -> System.out.println(str + " "))
                // 这里先变为大写
                .map(str -> str.toUpperCase(Locale.ROOT))
                // 再次打印
                .peek(str -> System.out.println(str + " "))
                // 输出
                .forEach(System.out::println); // 所以最后的结果就是单一的单词，(banana)小大大,(orange)小大大,
    }
}
