package com.gold.多线程;

public class MyThread extends Thread {
    @Override
    public void run() {
        // 要多线程执行的代码
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) System.out.println("i: " + i);
        }
    }
}

class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // 目前main是1个线程，MyThread是1个线程
        myThread.start(); // call玩start之后就是第2个线程
        // 所以你下面写的这段代码属于main的，但是输出和run这个就会交叉，无法确定谁先输出
        // 不信可以试一下，每一次输出的顺序肯定都是不一样的
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) System.out.println("main() i: " + i);
        }
    }
}
