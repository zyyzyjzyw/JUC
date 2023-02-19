package com.tedu.java;

import java.util.concurrent.TimeUnit;

/**
 * @author： zyy
 * @date： 2023/2/19 18:22
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public class Sleeper {
    public static void sleep(int i){
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleep(double i){
        try {
            TimeUnit.MILLISECONDS.sleep((int) i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
