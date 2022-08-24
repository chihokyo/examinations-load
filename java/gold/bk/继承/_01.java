package com.gold.继承;

public class _01 {
    public static void main(String[] args) {
        Person p = new Person();
        Student s = new Student("Math");
        s.eat();
        s.sleep();
        s.study();
    }
}


class Person {
    String name;
    int age;

    public void eat() {
        System.out.println("eating");
    }

    public void sleep() {
        System.out.println("sleeping");
    }
}

class Student extends Person {
    String subject;

    public Student(String subject) {
        this.subject = subject;
    }
    public void study() {
        System.out.println("study " + this.subject);
    }
}