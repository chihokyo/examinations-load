package com.gold._01;

public class oop01 {

    /**
     * 属性 类的属性 field
     * 方法 类的方法 method
     */


    public static void main(String[] args) {
        // 每一次new就可以有一份内存空间(非static，所以p1和p2不是一个)
        Person p1 = new Person();
        p1.eat();
        p1.name = "chin";
        p1.speak("chinese");


        Person p2 = new Person();
        // 初始化的一些变量
        System.out.println(p2.name); // null
        System.out.println(p2.age); // 0
        System.out.println(p2.isGood); // false
    }


}

class Person {
    String name;
    int age;
    boolean isGood;

    public void eat(){
        System.out.println("Person eating");
    }
    public void speak(String language){
        System.out.println(this.name + " can speak " + language );
    }
}
