package _2190300408hw2;

import java.util.Scanner;
import java.lang.Math;

public class Solution02 {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("请输入一个整数：");

        int target = reader.nextInt();
        double tar = Math.sqrt((double) target);
        int res = 1;

        for (int i = 2; i <= tar; i++) {
            if (target % i == 0) {
                res += i;
                res += target / i;
            }
        }

        if (res == target) {
            System.out.println(target + " 是完全数");
        } else {
            System.out.println(target + " 不是完全数");
        }

    }
}
