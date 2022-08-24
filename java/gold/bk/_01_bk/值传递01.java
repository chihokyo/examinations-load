package com.gold._01_bk;

public class 值传递01 {
    public static void main(String[] args) {
        // 基本数据类型 不影响
        int m = 10;
        int n = m;
        System.out.println("m: " + m); // 10
        System.out.println("n: " + n); // 10
        n = 99;
        System.out.println("m: " + m); // 10
        System.out.println("n: " + n); // 99

        // 引用类型 是影响的
        int[] arr = new int[]{1, 99};
        swap(arr[0], arr[1]);
        System.out.println("arr[0]: " + arr[0]); // 1
        System.out.println("arr[1]: " + arr[1]); // 19
        /*===为什么上面的代码没有被交换？===*/
        /*===因为你相当于只是复制了一份值给下面的swap，swap内部的数据确实是交换了 但是这并不会影响arr===*/
        /*===那要怎么解决呢？===*/
        swapfix(arr, 0, 1); // 这里交换的就只能是index索引了，而不是数值本身
        System.out.println("fix arr[0]: " + arr[0]); // 99
        System.out.println("fix arr[1]: " + arr[1]); // 1

    }

    // 这是一个交换
    public static void swap(int x, int y) {
        System.out.println("before x: " + x);
        System.out.println("before y: " + y);
        int temp = x;
        x = y;
        y = temp;
        System.out.println("after x: " + x);
        System.out.println("after y: " + y);
    }

    public static void swapfix(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
