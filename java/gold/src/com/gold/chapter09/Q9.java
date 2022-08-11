package com.gold.chapter09;

public class Q9 {
    public static void main(String[] args) {
        try (TroubleResource troubleResource = new TroubleResource();) {
            throw new Exception();
        } catch (RuntimeException e) {
            System.out.println("A");
        } catch (Exception e) {
            // 一旦catch到了这里 后面就会被无视
            System.out.println("B");
        }
    }
}


// AutoCloseable 抛出的是 Exception 异常
class TroubleResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new RuntimeException("trouble");
    }
}