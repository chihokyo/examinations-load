package com.gold.charpter07;

public class Q4 {
    public static void main(String[] args) {
        // A和B有继承关系，但是泛型是不适用的
        // ❌ Item<A> a = new Item<B>();
        // ❌ Item<Object> a = new Item<A>();
        Item<A> a = new Item<A>(); // ✅
    }
}

class A {

}

class B extends A {

}


class Item<T> {

}