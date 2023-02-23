package com.tedu.java.demo01;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class TestDemo03 {
    static final int n = 2;
    static final int m = 200;
    public static void main(String[] args) {
        ThreadSafe test = new ThreadSafe();
        for (int i=0;i<n;i++){
            new Thread(()->{
                test.method1(m);
            },"Thread"+(i+1)).start();
        }
    }
}
class ThreadSafe{
    ArrayList<String> list = new ArrayList<>();
    public void method1(int loopNum){
        for(int i = 0;i<loopNum;i++){
            method2();
            method3();
        }
    }

    private void method3() {
        list.add("1");
    }

    private void method2() {
        list.remove(0);
    }
}
