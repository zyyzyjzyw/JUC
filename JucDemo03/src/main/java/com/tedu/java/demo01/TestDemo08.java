package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j(topic = "c.TestDemo08")
public class TestDemo08 {
    // 线程1等待线程2的结果
    public static void main(String[] args) {
       GuardedObject obj =  new GuardedObject();
       new Thread(()->{
           log.info("等待结果");
           // 等待结果
           List<String> htmlList =(List<String>) obj.get(2000);
           log.info("结果大小:{}",htmlList.size());

       },"t1").start();
        new Thread(()->{
            log.info("执行下载");
            // 执行下载
            try {
                List<String> list = Downloader.downLoad();
                obj.complete(list);
            } catch (IOException e) {
                e.printStackTrace();
            }

        },"t2").start();
    }
}
class GuardedObject{
    // 结果
    private Object response;

    // 获取结果  tomeOut最多等待多长时间
    public Object get(long timeOut){
        synchronized (this){
            // 记录开始时刻
            long begin = System.currentTimeMillis();
            // 经历的时间
            long passTime = 0;
            // 没有结果
            while (response==null){
                try {
                    if(passTime>=timeOut){
                        break;
                    }
                    this.wait(timeOut-passTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 经历的时间
                passTime=  System.currentTimeMillis()-begin;
            }
            return response;
        }
    }
    // 生成结果
    public void complete(Object response){
        synchronized (this){
            // 讲结果成员变量赋值
            this.response=response;
            this.notifyAll();
        }
    }
}
