package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j(topic = "c.TestDemo06")
public class TestDemo06 {
    final static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (obj){
                log.info("执行......");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("其他代码....");
            }
        },"t1").start();
        new Thread(()->{
            synchronized (obj){
                log.info("执行......");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("其他代码....");
            }
        },"t2").start();
        // 主线程两秒后执行
        sleep(2);
        log.info("唤醒Obj上其他线程");
        synchronized (obj){
            // 唤醒obj上一个线程
            //obj.notify();
            // 唤醒所有线程
            obj.notifyAll();
        }
    }
}
