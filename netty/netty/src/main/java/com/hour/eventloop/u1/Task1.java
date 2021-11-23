package com.hour.eventloop.u1;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class Task1 {
    
    final static Logger log = LoggerFactory.getLogger(Task1.class);

    public static void main(String[] args) {

        EventLoopGroup group = new  NioEventLoopGroup();

        // 核心数
        // int i = NettyRuntime.availableProcessors();

        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

        group.next().submit(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("ok");
        });

        group.next().execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("ok");
        });

        group.next().scheduleAtFixedRate(()->{
            log.debug("ok");
        }, 0, 2, TimeUnit.SECONDS);

        log.debug("ok");
    }
}
