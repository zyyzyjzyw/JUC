package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * @author zyy
 * @version 1.0.0
 * @ClassName TestDemo12.java
 * @Description TODO
 * @createTime 2023年03月04日 10:17:00
 */
@Slf4j(topic = "t.TestDemo12")
public class TestDemo12 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            log.info("尝试获得锁");
            boolean hasLock = false;
            try {
                hasLock = lock.tryLock(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!hasLock){
                log.info("获取不到锁");
                return;
            }
            try {
                log.info("获得到锁");
            }finally {
                lock.lock();
            }
        },"t1");
        lock.lock();
        log.info("主线程获取到锁");
        t1.start();
        sleep(1);
        lock.unlock();
        log.info("释放锁");
    }
}
