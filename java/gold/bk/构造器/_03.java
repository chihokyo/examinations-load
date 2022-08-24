package com.gold.构造器;

public class _03 {
    public static void main(String[] args) {
        // 当你写构造器没有写默认无参构造器的时候
        // 是不能使用 new PersonOne();
        // 但是可以使用你写的带参数的，无参并不是写的必须的。
        PersonOne po = new PersonOne("chin",18);
        po.info();
    }
}

class PersonOne {
    String name;
    int age;
    public PersonOne(String name, int age){
        this.name = name;
        this.age = age;
    }
    public void info () {
        String name = "hoho"; // 当有的时候默认找local
        // 需要特殊指名是对象的话需要加this
        System.out.println("name=" + this.name);
        System.out.println("name=" + name);
    }
}
