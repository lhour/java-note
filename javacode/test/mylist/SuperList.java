package mylist;


import java.util.*;
public class SuperList {
    
    public List<Object> list = new ArrayList<>();

    public SuperList() {

    }

    public void add(Object o){
        list.add(o);
    }

    public Object get(int i){

        return (list.get(i));
    }
    

}
