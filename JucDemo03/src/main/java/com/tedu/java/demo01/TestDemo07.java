package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;
@Slf4j
public class TestDemo07 {
    static final Object object =new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (object){
                log.info("获得锁");
                try {
                    //sleep(20000);
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        sleep(1);
        synchronized (object){
            log.info("获得锁");
        }
    }
}
