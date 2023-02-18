package com.tedu.java.testThread;




public class TestRunnable {
    public static void main(String[] args) {
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("Runnable....ing");
            }
        };
        Thread t = new Thread(runnable);
        // 启动线程
        t.start();
        System.out.println("main......ing");
    }
}
