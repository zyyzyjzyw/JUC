package com.tedu.java.testThread;

public class TestFrames {

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                method1(20);
            }
        };
        t1.setName("t1");
        t1.start();
        method1(10);
    }

    private static void method1(int i) {
        int y = i+1;
        Object m = method2();
        System.out.println(m);
    }

    private static Object method2() {
        Object n = new Object();
        return n;
    }


}
