package com.gold.内部类;

import org.junit.jupiter.api.Test;

public class _02 {
}

interface A {
    void foo();
}

class B {
    @Test
    public void bar() {
        // 这里你想临时调用A里面的foo
        // 这里就是匿名类的实现 其实就是看起来 new 接口()
        // 但实际上应该是你写了一个类实现了一个接口，但是没有写类名而已。
        call(new A() {
            @Override
            public void foo() {
                System.out.println("我是在B里面的foo哦");
            }
        });
    }

    public void call(A a) {
        a.foo();
    }
}
