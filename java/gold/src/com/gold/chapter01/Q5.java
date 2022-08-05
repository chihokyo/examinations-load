package com.gold.chapter01;

public class Q5 {
    void test() {
        Inner.msg = "Hello java";

    }

    class Inner {
        // 这里定义了一个static的field，但是你的class却不是static的。
        private static String msg;

        public void test() {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Q5 q5 = new Q5();
        q5.test();
        q5.new Inner().test();

    }

}
