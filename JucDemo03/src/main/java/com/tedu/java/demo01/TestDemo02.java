package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class TestDemo02 {
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(()->{
           log.info("begin");
            try {
                n1.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
           log.info("begin");
           n1.b();
        }).start();
        new Thread(()->{
            log.info("begin");
            n1.c();
        }).start();

    }
}
@Slf4j(topic = "c.Number")
class Number{
    public synchronized void a() throws InterruptedException {
        sleep(100);
        log.info("1");
    }

    public synchronized void b(){
        log.info("2");
    }
    public  void c(){
        log.info("3");
    }
}
