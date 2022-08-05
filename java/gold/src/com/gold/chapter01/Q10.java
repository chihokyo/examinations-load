package com.gold.chapter01;

public interface Q10 {
    static void test() {
        System.out.println("A");
    }
}

class SampleIml implements Q10 {
    // 不能被重写的，此处相当于就是 SampleIml自己的方法
    public static void test() {
        System.out.println("B");
    }

    public static void main(String[] args) {
        Q10.test(); // A
    }
}
