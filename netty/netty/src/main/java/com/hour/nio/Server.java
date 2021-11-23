package com.hour.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Server {

    static Logger log = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) throws IOException {

        //ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.configureBlocking(false);
        //绑定监听端口
        ssc.bind(new InetSocketAddress(8080));
        //连接集合
        List<SocketChannel> channels = new ArrayList<>();
        
        while(true) {
            // log.debug("connecting...");
            SocketChannel sc = ssc.accept();
            
            if(sc != null) {
                log.debug("connecting... {}", sc);
                sc.configureBlocking(false);
                channels.add(sc);
            }

            for(SocketChannel channel : channels) {
                // log.debug("before read... {}", channels);
                int read = channel.read(buffer);
                if(read > 0) {
                    buffer.flip();
                    log.debug("{}", new String(buffer.array()));
                    log.debug("{}", sc.getRemoteAddress());
                    buffer.clear();
                    // log.debug("after read... {}", channel);
                }
                
            }
        }
    }
}