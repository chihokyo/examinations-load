package com.gold.多态;

import java.util.Objects;

public class _02 {
    public static void main(String[] args) {
        Order o1 = new Order(1001, "AA");
        Order o2 = new Order(1001, "AA");

        System.out.println(o1.equals(o2));
    }
}


class Order {
    int orderId;
    String orderName;

    public Order(int orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Order) {
            // 强转 不强转就无法调用order里的属性or方法
            Order order = (Order) o;
            return this.orderId == ((Order) o).orderId && this.orderName.equals(((Order) o).orderName);
        }
        return false;
    }


}