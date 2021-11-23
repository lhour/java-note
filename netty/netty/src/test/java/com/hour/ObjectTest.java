package com.hour;

public class ObjectTest {

    int a = 10;

    ObjectTest(){
        getA();
    }

    public void getA(){
        System.out.print(a);
    }

    public static void main(String[] args) {
        SubClass sub = new SubClass();
    }
}

class SubClass extends ObjectTest {
    
    public void getA() {
        super.getA();
        System.out.print("2");
    }

    public static void main(String[] args) {
        SubClass sub = new SubClass();
    }
}