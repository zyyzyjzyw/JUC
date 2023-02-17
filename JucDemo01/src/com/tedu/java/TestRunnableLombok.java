package com.tedu.java;

public class TestRunnableLombok {
    public static void main(String[] args) {
        // 线程对象
        Runnable r = ()->{
            // 任务对象
            System.out.println("Runnable....ing");
        };
        Thread t = new Thread(r);
        // 线程启动
        t.start();
        System.out.println("main......ing");
    }
}
