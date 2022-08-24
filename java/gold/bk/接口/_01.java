package com.gold.接口;

/**
 * 接口不能定义构造器，所以不能被实例化
 * 抽象类可以，因为抽象类虽然不能实例化，但却有构造器供子类super()使用
 */
interface _01 {

    // 常量
    public final int 普通常量MAX = 99999;
    public final static int 全局常量MAX = 99999;
    // 因为在interface里只能定义全局常量
    int 省略MAX = 99999; // 可省略 public final static

    // 方法
    public abstract void fly();

    void fly02(); // 因为都是抽象方法 所以可以省略 public abstract

}

//二选一 ① 要么实现所有的接口方法。
class Plane implements _01 {


    @Override
    public void fly() {

    }

    @Override
    public void fly02() {

    }
}

// ② 要么本身变为抽象类
abstract class Ship implements _01 {
    @Override
    public void fly02() {

    }
}

//
class Bicyble extends Ship {

    @Override
    public void fly() {

    }
}
