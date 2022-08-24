package com.gold.继承;

public class _02_Person {

    private String name;
    int age;
    int score = 99;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println("eating");
    }

    public void sleep() {
        System.out.println("sleeping");
    }
}
