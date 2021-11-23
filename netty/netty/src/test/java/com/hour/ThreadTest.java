package com.hour;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Testable
public class ThreadTest {
    
    Logger log = LoggerFactory.getLogger(this.getClass());
    volatile Boolean a = false;

    @Test
    public void threadTest01() {

        new Thread(() -> {
            while(!a){
                log.debug("running...");
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                
            }
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            a = true;
        }).start();
    }
    
}
