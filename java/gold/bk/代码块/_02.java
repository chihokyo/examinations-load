package com.gold.代码块;

public class _02 {
    public static void main(String[] args) {
        Order o = new Order();
        System.out.println(o.orderId);
    }
}

class Order {
    int orderId = 1;
    static {
        int orderId = 2;
    }
    {
        // 代码块整体这里其实无关顺序
        // 但是代码块和显式声明是要有顺序的
        orderId = 3;
    }

    public Order() {
        orderId = 4;
    }

    public void setOrderId() {
        this.orderId = 5;
    }
}
