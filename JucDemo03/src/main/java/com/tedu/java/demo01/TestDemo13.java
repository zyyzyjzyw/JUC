package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;


/**
 * @author zyy
 * @version 1.0.0
 * @ClassName TestDemo13.java
 * @Description TODO
 * @createTime 2023年03月04日 18:54:00
 */
@Slf4j(topic = "c.TestDemo13")
public class TestDemo13 {
    //static final Object room = new Object();
    static boolean hasCigarette = false;
    static boolean hasTokeOut = false;
    static ReentrantLock ROOM = new ReentrantLock();
    // 等待烟的休息室
    static Condition waitCigaretteSet = ROOM.newCondition();
    // 等待外卖的休息室
    static Condition waitTokeOutSet = ROOM.newCondition();
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            ROOM.lock();
            try {
                log.info("有烟没?[{}]",hasCigarette);
                while (!hasCigarette){
                    log.info("没有烟,先歇会");
                    try {
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("可以开始干活了");
            }finally {
                ROOM.unlock();
            }
        },"小勇").start();
        new Thread(()->{
            ROOM.lock();
            try {
                log.info("外卖送到没?[{}]",hasTokeOut);
                while (!hasTokeOut){
                    log.info("没有外卖,先歇会");
                    try {
                        waitTokeOutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("可以开始干活了");
            }finally {
                ROOM.unlock();
            }
        },"小美").start();
        try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            ROOM.lock();
            try {
                hasTokeOut=true;
                waitTokeOutSet.signalAll();
            }finally {
                ROOM.unlock();
            }
        },"送外卖的").start();
        sleep(1);
        new Thread(()->{
            ROOM.lock();
            try {
                hasCigarette=true;
                waitCigaretteSet.signalAll();
            }finally {
                ROOM.unlock();
            }
        },"送烟的").start();

    }
}
