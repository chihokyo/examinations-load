package com.gold.charpter07;

import java.util.Map;
import java.util.Set;

public class Q15 {
    public static void main(String[] args) {
        var a = Map.entry(1, "A");
        var b = Map.entry(2, "B");
        var c = Map.entry(3, "C");
        Map<Integer, String> map = Map.ofEntries(a, b, c);
        // Set<Map.Entry<Integer, String>> entries = map.entrySet(); 这是idea给我生成的，本质一样
        // 只是把Map换成了Set，更具体了而已
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
