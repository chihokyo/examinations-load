package com.gold.chapter02;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Q13 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        // replaceAll方法这里接受一个
        /**
         * default void replaceAll(UnaryOperator<E> operator) {
         *         Objects.requireNonNull(operator);
         *         final ListIterator<E> li = this.listIterator();
         *         while (li.hasNext()) {
         *             li.set(operator.apply(li.next()));
         *         }
         *     }
         */

        list.replaceAll(x -> x.toUpperCase());
        for (String l : list) {
            System.out.println(l); // A B C
        }
    }
}
