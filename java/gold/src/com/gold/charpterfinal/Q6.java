package com.gold.charpterfinal;

import java.util.Comparator;

public class Q6 {
    public static void main(String[] args) {
        // 匿名实现类
        // 1 这里用泛型，下面就可以不用Object
        new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 2 这里返回String默认已经实现的，返回值正好是一个int
                return o1.compareTo(o2);
            }
        };
    }
}
