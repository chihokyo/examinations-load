package com.gold.枚举类;

public enum Season {
    SPRING(),
    SUMMER(),
    AUTUMN(),
    WINTER(),
}

class Test {
    public static void main(String[] args) {
        Season[] values = Season.values(); // 返回常量（对象）数组
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        Season w = Season.valueOf("WINTER");
        System.out.println(w);
    }
}