package com.gold.charpter07;

import java.util.Arrays;
import java.util.Comparator;

public class Q17 {
    public static void main(String[] args) {
        String[] arr = new String[]{"AA", "CC", "KK", "MM", "GG", "JJ", "DD"};
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    // 直接翻转一下就好
                    return -s1.compareTo(s2);
                }
                return 0;
            }
        });
        System.out.println(Arrays.toString(arr));

        // 用lambda写
        Arrays.sort(arr, (o1, o2) -> {
            if (o1 instanceof String && o2 instanceof String) {
                String s1 = (String) o1;
                String s2 = (String) o2;
                // 直接翻转一下就好
                return -s1.compareTo(s2);
            }
            return 0;
        });
        System.out.println(Arrays.toString(arr));
    }
}
