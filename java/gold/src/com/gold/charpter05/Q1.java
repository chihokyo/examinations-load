package com.gold.charpter05;


import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
 * 2. File类声明在java.io包下
 * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
 * 并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成。
 * 4. 后续File类的对象常会作为参数传递到流的构造器中，指明读取或写入的"终点".
 * <p>
 * <p>
 * 我感觉就是这个类只能处理文件和文件夹，相当于cd和mkdir和touch，顶多在stat一下。
 * 但是对于写入写出这些流的操作，这个类是不可以的。
 */
public class Q1 {

    @Test
    public void test01() {
        File f1 = new File("hello1.txt");

        System.out.println(f1);
        System.out.println(f1.getAbsolutePath());
        System.out.println(f1.getPath());
        System.out.println(f1.getName());

        File f2 = new File("/Users/chin/coolcode/examinations-load/java/gold");
        String[] list = f2.list();
        // 列出目录下所有文件
        for (String l : list) {
            System.out.println(l);
        }

        File[] files = f2.listFiles();
        // 列出目录下所有文件
        for (File f : files) {
            System.out.println(f);
        }
    }
}
