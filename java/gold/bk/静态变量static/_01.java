package com.gold.静态变量static;

public class _01 {
    public static void main(String[] args) {
        Person.nation = "alf";
        Person p = new Person();
        p.nation = "ko";
        p.live();

        Person.eat();
        p.eat();
    }


}

/**
 * 类只会加载1次，存在方法区的静态域里面
 */
class Person {
    static String nation = "china";
    String name = "chin";
    int age = 99;

    public void live() {
        String nation = "jp";
        System.out.println("live in " + nation);
        System.out.println("live in " + this.nation);
        System.out.println("live in " + Person.nation);
    }

    public static void eat() {
        System.out.println("eating");
    }
}