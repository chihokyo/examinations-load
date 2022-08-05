package com.gold.chapter01;

import java.util.ArrayList;
import java.util.List;

public class Q8 {
    public static void main(String[] args) {
        Sample sample = new Sample() {
            // 构造器是不会允许的
            // public Sample(){
            //     System.out.println("A");
            // }
        };

        //****************************************
        // 想要初始化，请用的代码块
        List<String> list = new ArrayList<>() {
            {
                super.add("A");
                super.add("B");
                super.add("C");
            }
        };
        for (String str : list) {
            System.out.println(str);
        }
    }

}

class Sample {
    public Sample() {
        System.out.println("A");
    }
}
