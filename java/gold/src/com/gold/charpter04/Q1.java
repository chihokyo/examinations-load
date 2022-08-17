package com.gold.charpter04;

import java.util.Optional;

public class Q1 {
    public static void main(String[] args) {
        // 1 可以创建一个空对象
        Optional<Object> op1 = Optional.empty();
        System.out.println(op1); // Optional.empty

        String str = null;
        // 2 要求非空 否则报错
        // Optional<String> op2 = Optional.of(str); // NullPointerException

        // 3 可以为空，打印出来
        Optional<String> op3 = Optional.ofNullable(str);
        System.out.println(op3); // Optional.empty

        // 4 如果Optional内部的value非空，则返回此value值。
        String strForNull = op3.orElse("为空的时候我就出现了");
        System.out.println(strForNull); // 为空的时候我就出现了
    }
}
