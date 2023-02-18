package com.tedu.java;

import org.apache.log4j.Logger;
import org.junit.Test;


public class ThreadTest01 {
    static Logger log = Logger.getLogger(ThreadTest01.class);
    @Test
    public void testRun(){
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug(Thread.currentThread().getName());

            }
        };
    }

}
