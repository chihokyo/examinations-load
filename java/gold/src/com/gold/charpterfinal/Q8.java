package com.gold.charpterfinal;

public class Q8 {

}

interface Test {
    public default void execute(String str) {
        System.out.println("A");
    }
}

abstract class AbstractClass {
    private void execute(String str) {
        System.out.println("B");
    }
}
// 这里实现了接口继承了抽象类。
// 编译是不会报错的，因为编译器把上面2个接口和类当成各自独立的方法
// 并且编译时期，是类优先于接口！！所以会使用
// 但是执行会出错，因为这里并没有说明你要调用哪个方法。一定要说明。
class Sample extends AbstractClass implements Test {
    public static void main(String[] args) {
        new Sample().execute("hello"); // IllegalAccessError
    }
}
