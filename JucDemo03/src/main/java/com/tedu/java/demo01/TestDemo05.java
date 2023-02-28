package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestDemo05")
public class TestDemo05 {
    static final Object lock = new Object();

    public static void main(String[] args) {
        try {
            synchronized (lock){
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
