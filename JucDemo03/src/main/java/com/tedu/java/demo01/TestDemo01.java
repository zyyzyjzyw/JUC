package com.tedu.java.demo01;


import lombok.extern.slf4j.Slf4j;

/**
 * @author： zyy
 * @date： 2023/2/20 20:06
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Slf4j(topic = "c.TestDemo01")
public class TestDemo01 {
    //static int counter = 0;
    //static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
       Thread t1 =  new Thread(()->{
            for (int i=0;i<5000;i++){
                /*synchronized (lock){
                    counter++;
                }*/
                room.increment();

            }
        });
        Thread t2 = new Thread(()->{
            for (int i=0;i<5000;i++){
               /* synchronized (lock){
                    counter++;
                }*/
                room.decrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //log.info("{}",counter+"**********************************");
        log.info("{}",room.getCount());
    }
}
class Room {
    private int counter = 0;
    public void increment(){
        synchronized (this){
            counter++;
        }
    }
    public void decrement(){
        synchronized (this){
            counter--;
        }
    }
    public int getCount(){
        synchronized (this){
            return counter;
        }
    }
}
