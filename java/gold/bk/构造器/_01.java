package com.gold.构造器;

/**
 * 初体验
 * 构造器很像特殊的方法，但不是方法。
 * 构造器的本质和功能就是造对象的。
 * 1 创建对象
 * 2 初始化对象信息（初始化的权限跟类的权限相同）
 * 3 一个类中定义多个构造器，彼此构成重载
 * 4 一旦显式的定义了构造器之后，系统就不在默认提供空参构造器
 */
public class _01 {

    public static void main(String[] args) {
        Person p = new Person();
    }
}


class Person {
    // 下面就是一个构造器，不写系统默认给你生成一个空的 如↓
    public Person() {

    }

    // 重载
    public Person(String name) {
        System.out.println(name);
    }
}
