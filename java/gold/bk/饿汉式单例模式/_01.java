package com.gold.饿汉式单例模式;

public class _01 {
    public static void main(String[] args) {
        Bank b1 = Bank.getInstance();
        Bank b2 = Bank.getInstance();
        System.out.println(b1.equals(b2)); // true
        System.out.println(b1 == b2); // true
    }
}


class Bank {
    // 1 私有化 保证外部不可new
    private Bank() {

    }

    // 2 内部创建类的对象（创建步骤就在这里
    // 4 既然getInstance是静态的，那么里面用的属性也必须是静态的 +static
    private static Bank instance = new Bank();

    // 3 给外部一个获取instance的方法，但由于外部能new，所以设置成为static
    // 这样就可以直接获取
    public static Bank getInstance() {
        return instance;
    }
}