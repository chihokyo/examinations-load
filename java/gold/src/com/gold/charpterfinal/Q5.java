package com.gold.charpterfinal;

import java.util.List;

public class Q5 {
    public static void main(String[] args) {
        List<Item> list = List.of(
                new Item("apple", 100),
                new Item("banana", 80),
                new Item("orange", 120)
        );

        double result = list.stream().filter(e -> e.getName().equals("apple"))
                .mapToInt(Item::getPrice)
                .average()
                .getAsDouble();
        System.out.println(result); // 100.00
    }
}


class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}