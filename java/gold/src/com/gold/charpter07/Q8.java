package com.gold.charpter07;

import java.util.ArrayList;
import java.util.List;

public class Q8 {
    public static void main(String[] args) {
        List<? extends Number> list = new ArrayList<Integer>();
        // list.add(1); 除了add(null) 其他都无法添加
        // list.add(2);
    }
}
