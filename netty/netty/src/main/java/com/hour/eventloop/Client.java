package com.hour.eventloop;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class Client {
    
    public static void main(String[] args) throws InterruptedException {
        Channel channel = new Bootstrap()
            .group(new NioEventLoopGroup())
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel sc) throws Exception {
                    sc.pipeline().addLast(new StringEncoder());
                };
            })
            .connect(new InetSocketAddress("127.0.0.1", 8080))
            .sync()
            .channel();
            
        System.out.println("close");
    }
}
