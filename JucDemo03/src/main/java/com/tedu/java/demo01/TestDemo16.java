package com.tedu.java.demo01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zyy
 * @version 1.0.0
 * @ClassName TestDemo16.java
 * @Description TODO
 * @createTime 2023年03月04日 21:13:00
 */
public class TestDemo16 {
    public static void main(String[] args) throws InterruptedException {
        AwaitSignal awaitSignal = new AwaitSignal(5);
        Condition a = awaitSignal.newCondition();
        Condition b = awaitSignal.newCondition();
        Condition c = awaitSignal.newCondition();
        new Thread(()->{
            awaitSignal.print("a",a,b);
        }).start();
        new Thread(()->{
            awaitSignal.print("b",b,c);
        }).start();
        new Thread(()->{
            awaitSignal.print("c",c,a);
        }).start();
        Thread.sleep(1000);
        awaitSignal.lock();

        try {
            a.signal();
        }finally {
            awaitSignal.unlock();
        }
    }


}

class AwaitSignal extends ReentrantLock{
    private int loopNumber;

    public AwaitSignal(int loopNumber) {
        this.loopNumber = loopNumber;
    }
    // 参数1 打印内容 参数2:进入哪一间休息室,next:下一间休息室
    public void print(String str,Condition condition,Condition next){
        for (int i = 0; i < loopNumber; i++) {
            lock();
            try {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(str);
                next.signal();
            }finally {
                unlock();
            }
        }
    }
}
