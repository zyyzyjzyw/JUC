package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zyy
 * @version 1.0.0
 * @ClassName TestDemo11.java
 * @Description TODO
 * @createTime 2023年03月04日 09:41:00
 */
@Slf4j(topic = "c.TestDemo11")
public class TestDemo11 {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(()->{
           try {
               // 如果没有竞争状态那么此方法就会获得对象锁
               // 如果有竞争状态就进入阻塞队列，可以被其他线程用interruput方法打断
               log.info("尝试获得锁");
               lock.lockInterruptibly();
           } catch (InterruptedException e) {
               e.printStackTrace();
               log.info("没有获取到锁，返回");
               return;
           }
           try {
               log.info("获取到锁....");
           }finally {
               lock.unlock();
           }
       },"t1");
       lock.lock();
       t1.start();
       Thread.sleep(1000);
       log.info("打断他t1");
       t1.interrupt();
    }
}
