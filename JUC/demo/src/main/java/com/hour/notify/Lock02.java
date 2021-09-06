package com.hour.notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share3 {
    private int flag = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void method1() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1) {
                condition1.await();
            }
            for(int i = 0; i < 5; i ++){
                System.out.println(Thread.currentThread().getName() + " : " + flag);
            }
            flag = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }

    }

    public void method2() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2) {
                condition2.await();
            }
            for(int i = 0; i < 5; i ++){
                System.out.println(Thread.currentThread().getName() + " : " + flag);
            }
            flag = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void method3() throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3) {
                condition3.await();
            }
            for(int i = 0; i < 5; i ++){
                System.out.println(Thread.currentThread().getName() + " : " + flag);
            }
            flag = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }

}

public class Lock02 {
    public static void main(String[] args) {
        Share3 share = new Share3();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.method1();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.method2();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    share.method3();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "C").start();

    }
}
