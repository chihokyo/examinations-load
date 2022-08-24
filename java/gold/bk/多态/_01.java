package com.gold.多态;

public class _01 {
    public static void main(String[] args) {
        // 父类的引用指向子类的对象
        // 父类类型 = 子类对象
        Animal a = new Dog();
        a.eat();
    }
}


class Animal {
    String name;
    int age;

    public void eat() {
        System.out.println("eating");
    }
}

class Dog extends Animal {
    int leg;

    public void barking() {
        System.out.println("barking");
    }

    public void eat() {
        System.out.println("dog eating");
    }
}

class Cat extends Animal {
    int eye;

    public void walking() {
        System.out.println("walking");
    }

    public void eat() {
        System.out.println("cat eating");
    }
}