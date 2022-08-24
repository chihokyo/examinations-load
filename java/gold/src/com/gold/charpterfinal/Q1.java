package com.gold.charpterfinal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("in : ");
            String input = br.readLine();
            System.out.println("out : " + input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
