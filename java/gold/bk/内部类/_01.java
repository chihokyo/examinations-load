package com.gold.内部类;

public class _01 {
}

class Student {
    // 成员静态类（什么都可以用来修饰，4大修饰，af都可
    private static class Teacher {

    }

    // 成员非静态类（除了static，其他都可
    private class Teacher2 {

    }

    public Student() {
        // 构造器：局部内部类（local class 只允许abstract和final 来修饰）
        abstract class Teacher3 {

        }
    }

    public void learn() {
        // 方法内：局部内部类（local class 只允许abstract和final 来修饰）
        final class Teacher4 {

        }
    }

    {
        // 代码块：局部内部类（local class 只允许abstract和final 来修饰）
        final class Teacher5 {

        }
    }

}