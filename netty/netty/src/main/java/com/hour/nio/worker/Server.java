package com.hour.nio.worker;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    static Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException {

        Thread.currentThread().setName("boss");
        // Selector 管理多个 channel
        Selector boss = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 建立 selector 和 channel 的联系
        SelectionKey sscKey = ssc.register(boss, 0);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8080));

        Worker worker = new Worker("worker-0");
        

        while (true) {
            // 没有事件发生时，线程会阻塞
            boss.select();
            Iterator<SelectionKey> iter = boss.selectedKeys().iterator();
            
            while (iter.hasNext()) {

                SelectionKey key = iter.next();
                // 同一个 key 一致留在里面，再次循环的时候，会报空指针异常
                iter.remove();
                log.debug("key:{}", key);

                // 区分事件类型
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    log.debug("{}", sc);
                    sc.configureBlocking(false);
                    // 这里会有空指针异常， 重点！！！
                    worker.register();
                    sc.register(worker.selecter, SelectionKey.OP_READ);
                } 
            }
        }
    }

    static class Worker implements Runnable {

        private Thread thread;

        private Selector selecter;

        private String name;

        public AtomicBoolean start = new AtomicBoolean(false);

        Lock lock = new ReentrantLock();

        public Worker(String name) {
            this.name = name;
        }

        public void register() throws IOException {
            if (start.compareAndSet(false, true)) {
                thread = new Thread(this, name);
                thread.start();
                selecter = Selector.open();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selecter.select();
                    Iterator<SelectionKey> iterator = selecter.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) key.channel();
                            channel.read(buffer);
                            buffer.flip();
                            log.debug("{}", selecter);
                            log.debug("{}", new String(buffer.array()));
                        }
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }
    }
}
