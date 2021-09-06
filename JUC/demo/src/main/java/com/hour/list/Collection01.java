package com.hour.list;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Collection01 {
    public static void main(String[] args) {
        Random r = new Random();
        
        List<Integer> list = Collections.synchronizedList(new ArrayList());
        for(int i = 0; i < 5; i ++){
            new Thread(
                () -> {
                    for(int j = 0; j < 5; j ++){
                        list.add(r.nextInt(100));
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
