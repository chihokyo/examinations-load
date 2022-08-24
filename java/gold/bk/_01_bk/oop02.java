package com.gold._01_bk;

/**
 * 属性的区别
 * 在类里面定义的是【成员变量】
 * 在方法里，构造方法里，代码块里，形参等等都是【局部（local）变量】
 * 声明在哪里，就定义在哪里
 *
 * 【区别】
 * 相同 格式一样 / 都要先声明后使用后 / 都有其对应的作用域
 * 不同
 *     1 定义的位置不同
 *          成员变量 field → 定义在类的{}内
 *          局部变量 → 定义在方法内，形参，构造方法，代码块
 *     2 权限修饰符不同
 *          成员变量 field → private/public/protected/缺省 （可见性）
 *          局部变量 → 不可使用（除非final）
 *     3 初始化
 *          field → 可以只声明，不用初始化
 *          local → 必须要初始化（※除了在形参里面，因为形参这里在调用的时候写参数就可以）
 *     4 内存加载位置
 *          field → 加载到 heap
 *          local → 加载到 stack
 *
 */
public class oop02 {
    String name; // 成员变量

    public static void main(String[] args) { // args 方法形参 → 局部变量
        int age = 5; // age 形参 → 方法内
        String nameNG; // ❌ 不同于field，这里没被初始化就不能用
        // System.out.println(nameNG); // Variable 'name' might not have been initialized
        String nameOk = "chin";
        System.out.println(nameOk);


        User u1 = new User();
        u1.study("math"); // 1-b 可以在调用初始化啊
    }
}

class User {
    String name;
    int age;
    void study (String subject) { // ? 1-a这里的subject没有初始化啊
        System.out.println("learning " + subject);
    }
}
