package com.tedu.java.testThread;


public class TestThread {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("running");
            }
        };
        t.start();
        System.out.println("maining");
    }
}
