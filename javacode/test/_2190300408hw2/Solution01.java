package _2190300408hw2;

import java.util.Scanner;

public class Solution01 {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("请输入 0-100 之间的一个分数：");
        int score = reader.nextInt();

        String res = "D";

        /**
         * （0，100）- 50 得 （-50，50） （-50，50）/ 51 正好是 0 其余情况均不为 0
         */
        switch ((score - 50) / 51) {
            case 0:
                break;
            default:
                System.out.println("输入异常，请检查确保输入的值在 0-100 之间");
                return;
        }

        switch ((score - 60) / 5) {
            case 8:
            case 7:
            case 6:
            case 5:
                res = "A";
                break;
            case 4:
            case 3:
                res = "B";
                break;
            case 2:
            case 1:
            case 0:
                res = "C";
        }
        System.out.println("等级：" + res);

    }
}