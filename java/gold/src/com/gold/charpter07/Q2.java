package com.gold.charpter07;

public class Q2 {
    public static void main(String[] args) {
        Value v = new Value("Hello");
        Object val = v.getVal();
        // 所有的不确定泛型都可以写成Object，准没错。
    }
}

class Value<T> {
    T val;

    public Value(T val) {
        super();
        this.val = val;
    }

    public T getVal() {
        return val;
    }
}
