package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zyy
 * @version 1.0.0
 * @ClassName TestDemo14.java
 * @Description TODO
 * @createTime 2023年03月04日 19:52:00
 * 同步模式之顺序控制:固定运行顺序
 */
@Slf4j(topic = "c.TestDemo14")
public class TestDemo14 {
    static final Object obj = new Object();
    static boolean t2Runned = false;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            synchronized (obj){
                while (!t2Runned){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                log.info("1");
            }
        },"t1");
        Thread t2 = new Thread(()->{
            synchronized (obj){
                log.info("2");
                t2Runned=true;
                obj.notify();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
