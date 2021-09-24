package _2190300408hw3;

import java.util.Random;

public class Solution {
    public static void main(String[] args) {

        Random r = new Random();
        int[] arr = new int[8];
        
        for(int i = 0; i < 8; i ++) {
            arr[i] = r.nextInt(100);
        }
        sort(arr);

        for(int i : arr) {
            System.out.println(i);
        }
    }

    public static void sort(int[] array){

        for (int i = 1; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return;
    }
}