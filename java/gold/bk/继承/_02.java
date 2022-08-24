package com.gold.继承;

public class _02 {
    public static void main(String[] args) {
        _02_Student s = new _02_Student("Math");
        // s.name = "chin"; 虽然继承过来了，但是也不能说直接使用的
        // 但是可以通过set和get来用
        s.setName("chin2");
        System.out.println(s.getName());
        s.showScore();
    }
}