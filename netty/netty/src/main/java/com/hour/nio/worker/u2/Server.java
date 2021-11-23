package com.hour.nio.worker.u2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
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

        Worker[] workers = new Worker[10];
        for(int i = 0; i < 10 ; i ++) {
            workers[i] = new Worker("worker-" + i);
        }
        AtomicInteger index = new AtomicInteger(0);

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
                    sc.configureBlocking(false);
                    workers[index.getAndAdd(1) % workers.length].register(sc);
                    log.debug("{}", sc);
                } 
            }
        }
    }

    static class Worker implements Runnable {

        private Thread thread;

        LinkedList<Integer> l = new LinkedList<>();

        private Selector selector;

        private String name;

        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();

        public AtomicBoolean start = new AtomicBoolean(false);

        Lock lock = new ReentrantLock();

        public Worker(String name) {
            this.name = name;
        }

        public void register(SocketChannel sc) throws IOException {
            if (start.compareAndSet(false, true)) {
                thread = new Thread(this, name);
                selector = Selector.open();
                thread.start();
            }

            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ);
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            selector.wakeup();
            log.debug("{}", sc);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Runnable task = queue.poll();
                    if(task != null){
                        task.run();
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            
                            try{
                                ByteBuffer buffer = ByteBuffer.allocate(16);
                                SocketChannel channel = (SocketChannel) key.channel();
                                int read = channel.read(buffer);
                                if(read == -1) {
                                    key.cancel();
                                }else{
                                    buffer.flip();
                                    log.debug("{}断开连接", channel);
                                    log.debug("{}", new String(buffer.array()));
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                key.cancel();
                                log.debug("断开连接");
                            }
                            
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
