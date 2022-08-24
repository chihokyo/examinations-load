package com.gold.charpterfinal;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Q2 {
    public static void main(String[] args) throws Exception {
        String fileName = "";
        Stream<String> lines = Files.lines(Paths.get(fileName));
        // 题目给的是这种 类型推断
        var a = Files.lines(Paths.get(fileName));
    }
}
