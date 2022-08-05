package com.gold.chapter01;

public class Q6 {

    void test() {
        // 这个name必须在Sample这个类之前定义
        // 而且必须是final的，jdk7之后默认都是final的。
        String name = "sample";

        class Sample {
            void hello() {
                // ❌ 不能修改 name = "yes";
                System.out.println("hello" + name);
            }
        }
        // ❌ String name = "sample";

    }
}
