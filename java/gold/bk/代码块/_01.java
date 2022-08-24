package com.gold.代码块;

public class _01 {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();
    }
}

class Person {
    // 只要类创建就执行（只会执行1次）
    // 作用就是初始化属性
    static {
        System.out.println("我是静态代码块");
    }

    // 只要new对象，随着对象创建执行就执行（每new1下就执行1次）
    // 作用就是初始化对象的
    {
        System.out.println("我是一般代码块");
    }
}
