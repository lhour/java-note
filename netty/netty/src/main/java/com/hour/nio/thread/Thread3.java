package com.hour.nio.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Thread3 {
    public static void main(String[] args) {
        FutureTask<Integer> fTask = new FutureTask<>(
            () -> {
                int sum = 0;
                for (int i = 1; i < 101; i++) {
                    sum += i;
                }
                System.out.println(Thread.currentThread().getName() + " is running: " + sum);
                return sum;
            }
        );
        new Thread(fTask).start();
        try {
            System.out.print(fTask.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
