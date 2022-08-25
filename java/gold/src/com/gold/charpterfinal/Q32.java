package com.gold.charpterfinal;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Q32 {
    public static void main(String[] args) {
        Stream<Integer> a = Stream.of(1, 2, 3, 4, 5);
        IntStream b = a.mapToInt(n -> n);
        DoubleStream c = b.mapToDouble(n -> n);
        // 上面都是从Steam转换成下一级的 IntStream和 DoubleStream 这是可以的
        // ❌ 但是下面相当于从 IntStream 转换成Stream不可以 因为这俩是同级的 都是实现了 BaseStream
        // ❌ Stream<Integer> d = c.mapToInt(n->n);
    }
}
