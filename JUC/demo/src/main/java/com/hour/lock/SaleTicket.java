package com.hour.lock;

import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }).start();

    }

}

class Ticket {

    private int number = 30;

    // 可重入锁
    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {

        lock.lock();

        try {
            if (number > 0) {
                number--;
                System.out.println(Thread.currentThread().getName() + "剩余：" + number);
            } else {
                System.out.println(Thread.currentThread().getName() + "卖完了");
            }
        } finally {
            lock.unlock();
        }
    }

}