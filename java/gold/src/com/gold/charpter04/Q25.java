package com.gold.charpter04;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

public class Q25 {
    public static void main(String[] args) {
        Map<String, IntSummaryStatistics> map = Arrays.asList(new Book("A", 1000), new Book("B", 2000), new Book("A", 500))
                .stream()
                .collect(Collectors.groupingBy(
                        Book::getTitle,
                        Collectors.summarizingInt(Book::getPrice)
                ));
        map.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

    }
}

class Book {
    private String title;
    private int price;

    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }
}
