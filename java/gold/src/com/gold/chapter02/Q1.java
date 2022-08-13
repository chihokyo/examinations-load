package com.gold.chapter02;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Q1 {

    /**
     * Consumer小试牛刀
     */
    @Test
    public void test01() {
        // 方式1
        consumerTest(100, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("consumer: " + aDouble); // consumer: 100.0
            }
        });
        // 方式2 lambda表达式
        consumerTest(1200, money -> System.out.println("consumer2: " + money));
        // 方式3 方法引用
        PrintStream ps = System.out;
        consumerTest(1200, ps::println);
    }

    public void consumerTest(double money, Consumer<Double> con) {
        con.accept(money);
    }


    @Test
    public void test02() {
        List<String> list = Arrays.asList("01", "011", "02");
        // 方式1
        List<String> filterList = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("1");
            }
        });
        System.out.println(filterList);

        // 方式2
        List<String> filterList2 = filterString(list, s -> s.contains("1"));
        System.out.println(filterList2);

    }

    public List<String> filterString(List<String> list, Predicate<String> pre) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                arrayList.add(s);
            }
        }
        return arrayList;
    }

    @Test
    public void test3() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(com1.compare(2, 4)); // -1
        System.out.println(com2.compare(8, 4)); // 1

        Comparator<Integer> com3 = Integer::compare; // 方法引用
        System.out.println(com2.compare(81, 4)); // 1
    }

    @Test
    public void test04() {
        // 传入Double，返回Long
        Function<Double, Long> func = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(func.apply(9.99)); // 10
        System.out.println(func.apply(5.22)); // 5
        // lambda
        Function<Double,Long> func2 = (d) -> Math.round(d);
        System.out.println(func2.apply(9.99)); // 10
        System.out.println(func2.apply(5.22)); // 5
        // 方法引用
        Function<Double,Long> func3 = Math::round;
        System.out.println(func3.apply(9.99)); // 10
        System.out.println(func3.apply(5.22)); // 5
    }

}


