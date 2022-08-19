package com.gold.charpter04;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Q15 {
    public static void main(String[] args) {
        // 这一题我都不想写，意思就是说distinct是要看equal的，但是这里全是true，判断全都是一个
        List<Value> list = Arrays.asList(
                new Value("A"),
                new Value("B"),
                new Value("C"),
                new Value("A")
        );

        long count = list.stream().distinct().count();
        System.out.println(count); //

    }
}

class Value {
    private String data;

    public Value(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 100;
    }
}