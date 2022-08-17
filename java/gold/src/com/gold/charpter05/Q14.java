package com.gold.charpter05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Q14 {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("a", "b");
        Path file = dir.resolve(Paths.get("data.txt"));
        // 相当于在指定文件夹下创建一个文件 a/b/data.txt
        Files.createFile(file);
    }
}

