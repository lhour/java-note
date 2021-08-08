package Interview;

public class Q1 {
    public static void main(String[] args) {
        System.out.println(get());
    }

    public static int get() {
        int num = 10;
        try{
            return num;
        }finally{
            num ++;
            System.out.println("finally : " + num);
        }
    }
}

/**
 * finally : 11
 * 10
 */

