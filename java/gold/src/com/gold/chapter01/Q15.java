package com.gold.chapter01;

public enum Q15 {

    RED, GREEN, BLUE;
}


class TestQ15 {
    public static void main(String[] args) {
        // 获取所有的枚举类的值 返回数组，所以可以使用索引
        System.out.println("*****************");
        Q15[] arrs = Q15.values();
        for (Q15 arr : arrs) {
            System.out.println(arr);
        }
        System.out.println("*****************");
        System.out.println(arrs[0]); // RED
        System.out.println("*****************");
        // 获取枚举常量
        System.out.println(Q15.valueOf("RED")); // RED
    }
}