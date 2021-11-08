package com.hour.selector.u1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    
    static Logger log = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) throws IOException {

        //Selector 管理多个 channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        //建立 selector 和 channel 的联系
        SelectionKey sscKey = ssc.register(selector, 0);

        sscKey.interestOps(SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8080));

        while(true) {
            //没有事件发生时，线程会阻塞
            selector.select();

            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();

            while(iter.hasNext()){
                SelectionKey key = iter.next();
                log.debug("key:{}", key);
                ServerSocketChannel channel =(ServerSocketChannel) key.channel();
                SocketChannel sc = channel.accept();
                log.debug("{}", sc);
            }
        }
    }
}
