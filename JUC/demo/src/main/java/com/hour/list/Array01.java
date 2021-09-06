package com.hour.list;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Array01 {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        for(int i = 0; i < 5; i ++){
            new Thread(
                () -> {
                    for(int j = 0; j < 5; j ++){
                        list.add(UUID.randomUUID().toString().substring(0,8));
                        System.out.println(list);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    
                }
            , i + "").start();;
        }
    }
}
