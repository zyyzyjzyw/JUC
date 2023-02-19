package com.tedu.java;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;


public class ThreadTest01 {

    static Logger log = Logger.getLogger(ThreadTest01.class);
    @Test
    public void testRun(){
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.info("running......");
            }
        };
        t1.run();
    }

    @Test
    public void test01(){
      Thread t1 =   new Thread("t1"){
            @Override
            public void run() {
                log.debug("running......");
            }
        };
        System.out.println(t1.getState());
        // start()方法不能被多次调用
        t1.start();
        System.out.println(t1.getState());
    }

    @Test
    public void testSleep(){
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        log.debug(t1.getState());
        t1.start();
        log.debug(t1.getState());
        try {
            sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testInterrupt() throws InterruptedException {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug("enter sleep.....");
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("wake up.....");
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        sleep(1000);
        log.debug("interrupt....");
        t1.interrupt();
    }

    @Test
    public void testTimeUnit() throws InterruptedException {
        log.debug("enter");
        TimeUnit.SECONDS.sleep(1);
        log.debug("end");
        //Thread.sleep(1000);
    }

    @Test
    public void testPriority(){
        Runnable task1 = ()->{
            int count = 0;
            for(;;){
                System.out.println("----->1"+count++);
            }
        };
        Runnable task2 = ()->{
            int count = 0;
            for(;;){
                /*Thread.yield();*/
                System.out.println("            ----->2"+count++);
            }
        };
        Thread t1 = new Thread(task1,"t1");
        Thread t2 = new Thread(task2,"t2");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }

    @Test
    public void testJoin1() throws InterruptedException {
        AtomicInteger r = new AtomicInteger();
        log.debug("开始");
       Thread t1 =  new Thread(()->{
            log.debug("开始");
            try {
                sleep(1);
                log.debug("结束");
                r.set(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t1.join();
        log.debug(r);
        log.debug("结束");
    }
    @Test
    public void testJoin2() throws InterruptedException {
        AtomicInteger r1 = new AtomicInteger();
        AtomicInteger r2 = new AtomicInteger();
        Thread t1 = new Thread(()->{
            try {
                sleep(1);
                r1.set(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                sleep(2);
                r2.set(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        log.debug("join begin");
        t1.join();
        log.debug("t1 join end");
        t2.join();
        log.debug("t2 join end");
        long end = System.currentTimeMillis();
        log.debug(end-start);
    }
    @Test
    public void testJoin3() throws InterruptedException {
        AtomicInteger r1 = new AtomicInteger();
        Thread t1 = new Thread(()->{
            try {
                sleep(2);
                r1.set(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long start = System.currentTimeMillis();
        t1.start();
        // 线程执行结束会导致join结束
        log.debug("join begin");
        t1.join(1500);
        long end = System.currentTimeMillis();
        log.debug(r1);
        log.debug(end-start);
    }

    /**
     * 打断sleep,wait,join的线程
     */
    @Test
    public void testInterrupt1() throws InterruptedException {
        Thread t1 = new Thread(()->{
            log.info("sleep......");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt......");
        t1.interrupt();
        log.debug(t1.isInterrupted());
    }
    /**
     * 打断正常运行的线程,不会被清空
     */
    @Test
    public void testInterrupt2() throws InterruptedException {
        Thread t1= new Thread(()->{
            while (true){
                boolean interrupted = Thread.currentThread().isInterrupted();
                if(interrupted){
                    log.debug("被打断了,退出循环");
                    break;
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
    }
    private Thread thread;
    @Test
    public void TwoPhaseTermination() throws InterruptedException {

        start();
        Thread.sleep(3500);
        stop();

    }
    public void start(){
        thread = new Thread(()->{
            while (true){
                Thread current = Thread.currentThread();
                if(current.isInterrupted()){
                    log.debug("料理后事");
                    break;
                }
                    try {
                        Thread.sleep(1000);
                        log.debug("执行监控记录");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 重新设置打断操作
                        current.interrupt();
                    }

            }
        });
        thread.start();
    }
    public void stop(){
        thread.interrupt();
    }
}
