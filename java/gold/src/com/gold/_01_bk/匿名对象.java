package com.gold._01;

public class 匿名对象 {
    public static void main(String[] args) {
        PhoneFactory pf = new PhoneFactory();
        // Phone p = new Phone(); 无需这么麻烦还需要更新一个变量
        pf.info(new Phone()); // 这就是匿名对象用法，
    }
}

class PhoneFactory {

    public void info(Phone phone) {
        phone.sendMail();
        phone.lMusic();
    }
}

class Phone {
    int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void sendMail() {
        System.out.println("sending Mail ....");
    }
    public void lMusic() {
        System.out.println("listening Music ....");
    }
}
