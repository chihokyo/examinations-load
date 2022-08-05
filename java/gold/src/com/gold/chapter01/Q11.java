package com.gold.chapter01;

public interface Q11 {
    default void test() {
        System.out.println("A");
    }
}

interface B11 {
    default void test() {
        System.out.println("B");
    }
}

interface C11 extends B11 {
}

interface D11 extends C11 {
}

class Sample10 implements Q11, D11 {
    @Override
    public void test() {
        Q11.super.test();
        // B11.super.test(); sample和10只是间接继承不可以的
        // C11.super.test(); 同上
    }
}
