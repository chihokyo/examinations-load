package com.gold.charpter06;

import java.util.Scanner;

public class Q9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("A,B,C,D,E");
        scanner.useDelimiter(",");
        try (scanner) {
            scanner.next();
            scanner.next();
            scanner.next();
            scanner.next();
            System.out.println(scanner.next()); // E
        }
    }
}