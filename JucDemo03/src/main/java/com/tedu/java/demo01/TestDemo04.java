package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j(topic = "c.TestDemo04")
public class TestDemo04 {
    public static void main(String[] args) {
        // 模拟多人买票
        TicketWindow window = new TicketWindow(10000);
        // 卖出的票数统计
        List<Integer> amountList = new Vector<>();
        for (int i = 0; i < 2000; i++) {
            new Thread(()->{
                int amount = window.sell(randomAmount());
                amountList.add(amount);
            });
        }
        // 统计卖出的票数和剩余的票数
       log.info("余票:{}",window.getCount());
        int amountM = 0;
        for (int i = 0; i <amountList.size() ; i++) {
            amountM+=amountList.get(i);
        }
        log.info("卖票:{}",amountM);
    }
    // 为线程安全
    static Random random = new Random();

    //随机1-5
    public static int randomAmount(){
        return random.nextInt(5)+1;
    }
}
//买票窗口
class TicketWindow{
    private int count;

    public TicketWindow(int count){
        this.count=count;
    }
    // 获取余票数量
    public int getCount(){
        return count;
    }
    // 售票
    public int sell(int amount){
        if(this.count>=amount){
            this.count-=amount;
            return count;
        }else {
            return 0;
        }
    }
}
