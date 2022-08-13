package com.gold.chapter02;

import java.util.function.BinaryOperator;

public class Q14 {
    public static void main(String[] args) {
        // 连接字符串
        BinaryOperator<String> b = (str, add) -> str.concat(add);
        System.out.println(b.apply("hello", "chin")); // hellochin
    }
}

// 下面是用一个工厂方法,工厂生产的只是方法体
class OperatorFactory {
    public static BinaryOperator<Integer> add() {
        return (a, b) -> a + b;
    }

    public static BinaryOperator<Integer> minus() {
        return (a, b) -> a - b;
    }
}

class UseOperatorFactory {
    public static void main(String[] args) {
        BinaryOperator<Integer> op = OperatorFactory.add();
        System.out.println(op.apply(1, 9)); // 10

        BinaryOperator<Integer> op2 = OperatorFactory.minus();
        System.out.println(op2.apply(1, 7)); // -6
    }
}