package com.gold.匿名子类;

public class _01 {
    public static void main(String[] args) {
        demo(new Order() {
            @Override
            void eat() {
                System.out.println("eating");
            }

            @Override
            void sleep() {
                System.out.println("sleeping");
            }

            @Override
            public String toString() {
                return "Order";
            }
        });
    }

    public static void demo(Order o) {
        System.out.println(o);
    }
}

abstract class Order {
    abstract void eat();

    abstract void sleep();
}


