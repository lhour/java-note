package com.hour.notify;

class Share {
    
    private int number = 0;

    public synchronized void recr() throws InterruptedException {
        if(number != 0){
            this.wait();
        }
        number ++;
        System.out.println(Thread.currentThread().getName() + " : " + number);
        this.notifyAll();
    }

    public synchronized void decr() throws InterruptedException {
        if(number != 1){
            this.wait();
        }
        number --;
        System.out.println(Thread.currentThread().getName() + " : " + number);
        this.notifyAll();
    }


}

public class Thread01 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++){
                    try {
                        share.recr();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        , "A").start();

        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++){
                    try {
                        share.decr();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        , "B").start();
        
        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++){
                    try {
                        share.recr();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        , "C").start();

        new Thread(
            () -> {
                for(int i = 0; i < 10; i ++){
                    try {
                        share.decr();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        , "D").start();
    }
}
