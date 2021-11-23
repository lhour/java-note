package com.hour.nio.selector.u1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
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

        // Selector 管理多个 channel
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 建立 selector 和 channel 的联系
        SelectionKey sscKey = ssc.register(selector, 0);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            // 没有事件发生时，线程会阻塞
            selector.select();
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();

            while (iter.hasNext()) {

                SelectionKey key = iter.next();
                // 同一个 key 一致留在里面，再次循环的时候，会报空指针异常
                iter.remove();
                log.debug("key:{}", key);

                // 区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey sckey = sc.register(selector, 0);
                    sckey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", sc);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer bf = ByteBuffer.allocate(16);
                        int read = channel.read(bf);
                        if (read == -1) {
                            key.cancel();
                            log.debug("{}已断开", channel);
                        } else {
                            bf.flip();
                            log.debug("{}", new String(bf.array()));
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        log.debug("异常断开");
                        key.cancel();
                    }

                }

            }
        }
    }
}
