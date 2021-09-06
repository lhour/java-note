package com.hour.list;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Vector01 {
    public static void main(String[] args) {
        Random r = new Random();
        
        List<Integer> list = new Vector<>();
        for(int i = 0; i < 5; i ++){
            new Thread(
                () -> {
                    for(int j = 0; j < 5; j ++){
                        list.add(r.nextInt(1000));
                        System.out.println(list);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    
                }
            , i + "").start();
        }
    }
}
