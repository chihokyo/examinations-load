package com.gold.charpter07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Q7 {
    public static void main(String[] args) {
        List<?> list = null;
        list.add(null); // 只被允许添加null，其他都不可以
        // list.add("AA"); ❌
        Object o = list.get(0); // ？默认的返回值类型都是Object
    }

}
