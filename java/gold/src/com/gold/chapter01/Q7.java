package com.gold.chapter01;

public class Q7 {
    public static void main(String[] args) {
        // 此处Object根本没有这个test方法，编译肯定是会报错的
        // 解决方法 Object 类型改成 var 类型推断一下
        var obj =  new Object() {
            public void test() {
                System.out.println("test");
            }
        };
        obj.test(); // java: cannot find symbol
    }

}
