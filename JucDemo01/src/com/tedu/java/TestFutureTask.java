package com.tedu.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建任务对象
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("FutureTask.....run");
                Thread.sleep(10000);
                return 100;
            }
        });
        Thread t = new Thread(task);
        t.start();
        System.out.println("main.......ing");
        System.out.println(task.get());
    }
}
