package com.gold.charpter04;

import java.util.Optional;

public class Q8 {
    public static void main(String[] args) {
        Optional<Integer> a = Optional.of(100);
        // Optional<Integer> b = a.map(price -> calc(price, 3));
        // 这里的返回值问题，①调用calc的时候返回的其实是一个Optional<Integer>，
        // ②但是map里面又返回了一个Optional 所以会有包裹
        Optional<Optional<Integer>> b = a.map(price -> calc(price, 3));
        System.out.println(b.get());

        System.out.println("******解决方法*******");
        Optional<Integer> b2 = a.flatMap(price -> calc(price, 3));
        System.out.println(b2.get());
    }

    private static Optional<Integer> calc(int price, int qty) {
        if (qty < 0) {
            return Optional.empty();
        }
        return Optional.of(price * qty);
    }
}

