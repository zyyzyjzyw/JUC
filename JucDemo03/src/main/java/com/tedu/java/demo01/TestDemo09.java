package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j(topic = "c.TestDemo09")
public class TestDemo09 {
    public static void main(String[] args) {
        Object A = new Object();
        Object B = new Object();
        Thread t1 = new Thread(()->{
            synchronized (A){
                log.info("lock A");
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B){
                    log.info("lock B");
                    log.info("操作....");
                }
            }

        },"t1");
        Thread t2 = new Thread(()->{
            synchronized (B){
                log.info("lock B");
                try {
                    sleep((long) 0.5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A){
                    log.info("lock A");
                    log.info("操作....");
                }
            }

        },"t2");
        t1.start();
        t2.start();
    }
}