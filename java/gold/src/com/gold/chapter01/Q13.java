package com.gold.chapter01;

public class Q13 {
}

interface A13 {
    public default void test() {
        System.out.println("A");
    }
}

abstract class B13 {
    // 必须是public的才可以 protected不行
    public void test() {
        System.out.println("B");
    }
}

class Sample13 extends B13 implements A13 {
    public static void main(String[] args) {
        new Sample13().test(); // B
    }
}