package com.tedu.java.demo02;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

/**
 * @author zyy
 * @version 1.0.0
 * @ClassName TestJvmDemo01.java
 * @Description TODO
 * @createTime 2023年03月05日 10:25:00
 */
@Slf4j(topic = "c.TestJvmDemo01")
public class TestJvmDemo01 {
    volatile static boolean run = true;
    // 锁对象
    final static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
      Thread t = new Thread(()->{
            while (run){
                //......
                synchronized (lock){
                    if(!run){
                        break;
                    }
                }
            }
        });
      t.start();
      sleep(1);
      log.info("停止 t");
      //线程t不会如预想的停下来
        synchronized (lock){
            run = false;
        }
    }

}
