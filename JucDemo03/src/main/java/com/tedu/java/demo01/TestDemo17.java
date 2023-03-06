package com.tedu.java.demo01;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zyy
 * @version 1.0.0
 * @ClassName TestDemo17.java
 * @Description TODO
 * @createTime 2023年03月04日 21:22:00
 */
public class TestDemo17 {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    public static void main(String[] args) {
        ParkUnPark parkUnPark = new ParkUnPark(5);
        t1 = new Thread(()->{
            parkUnPark.print("a",t2);
        });
        t2 = new Thread(()->{
            parkUnPark.print("b",t3);
        });
        t3 = new Thread(()->{
            parkUnPark.print("c",t1);
        });
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }
}
class ParkUnPark{
    private int loopNumber;

    public ParkUnPark(int loopNumber) {
        this.loopNumber = loopNumber;
    }

    public void print(String str,Thread next){
        for (int i = 0; i <loopNumber ; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(next);
        }
    }
}
