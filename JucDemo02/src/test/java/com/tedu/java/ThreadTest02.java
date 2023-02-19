package com.tedu.java;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

/**
 * @author： zyy
 * @date： 2023/2/19 17:36
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public class ThreadTest02 {
    static Logger log = Logger.getLogger(ThreadTest02.class);

    /**
     * 打断park线程,不会清空状态
     */
    @Test
    public void testPark() throws InterruptedException {
        Thread t1 = new Thread(()->{
            log.info("park......");
            LockSupport.park();
            log.debug("unpark");
            log.debug(Thread.currentThread().isInterrupted());
        },"t1");
        t1.start();
        sleep(1);
        t1.interrupt();

    }
    /**
     * 守护线程
     */
    @Test
    public void testShThread() throws InterruptedException {
       Thread t1 = new Thread(()->{
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    break;
                }
            }
           log.info("结束");
        },"t1");
       t1.setDaemon(true);
       t1.start();
       Thread.sleep(1000);
       log.info("结束");
    }

    /**
     * 获取线程状态
     */
    @Test
    public void testThreadState(){
        Thread t1 = new Thread(()->{
            log.debug("running.....");
        },"t1");
        Thread t2 = new Thread(()->{
            while (true){

            }
        });
        t2.start();
        Thread t3 = new Thread(()->{
            log.info("running");
        });
        t3.start();
        Thread t4 = new Thread(()->{
            synchronized (ThreadTest02.class){
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t4.start();
        Thread t5 = new Thread(()->{
            try {
                t2.join();//wait
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t5.start();
        Thread t6 = new Thread(()->{
            synchronized (ThreadTest02.class){
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t6.start();
        log.info(t1.getState());
        log.info(t2.getState());
        log.info(t3.getState());
        log.info(t4.getState());
        log.info(t5.getState());
        log.info(t6.getState());
    }
    @Test
    public void testTcsf(){
        Thread t1 = new Thread(()->{
            log.debug("洗水壶");
            Sleeper.sleep(1);
            log.debug("烧开水");
            Sleeper.sleep(5);
        },"老王");

        Thread t2 = new Thread(()->{
            log.debug("洗茶壶");
            Sleeper.sleep(1);
            log.debug("洗茶杯");
            Sleeper.sleep(2);
            log.debug("拿茶叶");
            Sleeper.sleep(1);
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("泡茶");
        },"老张");
        t1.start();
        t2.start();
    }
}
