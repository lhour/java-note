package com.hour.learn02;

public class SaleTicket {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    t.sale();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(
            () -> {
                for (int i = 0; i < 5; i++) {
                    t.sale();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        t1.start();
        t2.start();
    }
}

// 资源类 OOP
class Ticket {

    private int number = 50;

    public synchronized void sale() {
        
            if (number > 0) {
                number--;
                System.out.println(Thread.currentThread().getName() + "剩余：" + number);
            } else {
                System.out.println("已售完");
            }
    }
}