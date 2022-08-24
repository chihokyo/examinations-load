package com.gold.模板方法;

public class _01 {
    public static void main(String[] args) {
        BankTemplate bD = new DrawMoney();
        BankTemplate bS = new SaveMoney();

        bD.process();
        System.out.println("****************");
        bS.process();
    }
}

abstract class BankTemplate {
    public void step1() {
        System.out.println("====step1====");
    }

    public abstract void center();

    public void step2() {
        System.out.println("====step3====");
    }

    // final不能重写了
    public final void process() {
        this.step1();
        this.center(); // 具体需要你子类进行实现的
        this.step2();
    }
}

class DrawMoney extends BankTemplate {

    @Override
    public void center() {
        System.out.println("DrawMoney");
    }
}

class SaveMoney extends BankTemplate {

    @Override
    public void center() {
        System.out.println("saveMoney");
    }
}