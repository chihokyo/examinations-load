package com.gold._01;

public class 值传递经典例题 {

    public static void main(String[] args) {
        first();
    }

    public static void first() {
        int i = 5; // local 变量
        Value v = new Value(); // 1 新建第一个地址新的假定地址是0x100
        v.i = 25; //2 v.i 改为25
        second(v, i); // 3 重点，这里传递是0x100 还有 5
        System.out.println(v.i); // 9 这里的0x100.i 在第5部被改，也就是20了
    }

    public static void second(Value v, int i) {
        i = 0; // 4 改为0
        v.i = 20; // 5 v.i 改为20了
        Value val = new Value(); // 6 新建了一个地址假定是0x200
        v = val; // 7 0x100 → 0x200
        System.out.println(v.i + " " + i); // 8 这里相当于刚才的0x200 也就是15,0
    }
}

class Value {
    int i = 15;
}
