package List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class ListTest {
    

    //尾插
    public static void insert() {

        List<Integer> array = new ArrayList<>();
        long Start =System.currentTimeMillis();
        for(int i = 0; i < 100000000; i ++){
            array.add((int)Math.random()*10000);
        }
        long End = System.currentTimeMillis();
        System.out.println(End-Start);      //5069

        
        List<Integer> linked = new LinkedList<>();
        Start =System.currentTimeMillis();
        for(int i = 0; i < 100000000; i ++){
            linked.add((int)Math.random()*10000);
        }
        End = System.currentTimeMillis();
        System.out.println(End-Start);      //19755
    }

        //头插
        public static void insert_head() {

            List<Integer> array = new ArrayList<>();
            long Start =System.currentTimeMillis();
            for(int i = 0; i < 100000; i ++){
                array.add(0,(int)Math.random()*10000);
            }
            long End = System.currentTimeMillis();
            System.out.println(End-Start);      //498
    
            
            List<Integer> linked = new LinkedList<>();
            Start =System.currentTimeMillis();
            for(int i = 0; i < 100000; i ++){
                linked.add(0,(int)Math.random()*10000);
            }
            End = System.currentTimeMillis();
            System.out.println(End-Start);      //136
        }
    

    //sort排序
    public static void sort() {

        List<Integer> array = new ArrayList<>();
        
        for(int i = 0; i < 100000000; i ++){
            array.add((int)Math.random()*10000);
        }
        long Start =System.currentTimeMillis();
        Collections.sort(array);
        long End = System.currentTimeMillis();
        System.out.println(End-Start);      //181

        
        List<Integer> linked = new LinkedList<>();
        
        for(int i = 0; i < 100000000; i ++){
            linked.add((int)Math.random()*10000);
        }
        Start =System.currentTimeMillis();
        Collections.sort(linked);
        End = System.currentTimeMillis();
        System.out.println(End-Start);      //6173
    }

    public static void main(String[] args) {
        // sort();

        insert_head();
    

    }

    
    
}
