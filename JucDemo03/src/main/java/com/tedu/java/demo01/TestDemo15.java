package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zyy
 * @version 1.0.0
 * @ClassName TestDemo15.java
 * @Description TODO
 * @createTime 2023年03月04日 20:50:00
 */
@Slf4j(topic = "c.TestDemo15")
public class TestDemo15 {
    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify(1,5);
        new Thread(()->{
            wn.print("a",1,2);
        }).start();
        new Thread(()->{
            wn.print("b",2,3);
        }).start();
        new Thread(()->{
            wn.print("c",3,1);
        }).start();
    }

}

/**
 * 输出内容        等待标记     下一个标记
 *   a              1           2
 *   b              2           3
 *   c              3           1
 */
class WaitNotify{
    // 打印
    public void print(String str,int waitFlag,int nextFlag){
        for (int i = 0; i <loopNumber ; i++) {
            synchronized (this){
                while (flag!=waitFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                flag=nextFlag;
                this.notifyAll();
            }
        }
    }
    // 等待标记
    private int flag;
    // 循环次数
    private int loopNumber;

    public WaitNotify(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }
}
