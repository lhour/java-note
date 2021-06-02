package leetcode刷题;

import java.util.ArrayList;
import java.util.List;

public class Fun51 {
    private static List<Integer[]> ress = new ArrayList<Integer[]>();
    public List<Integer[]> solveNQueens(int n) {
        int[] res = new int[n];
        for(int i = 0; i < n; i ++){
            res[i] = -1;
        }
        dfs(res, 0);
        return ress;
    }

    public void dfs(int[] res,int p){
        //结束条件
        if(p == res.length){
            Integer[] s = new Integer[res.length];
            for(int i = 0; i < res.length; i ++){
                s[i] = res[i];
            }
            ress.add(s);
            return;
        }
        //取值
        for(int i = 0; i < res.length; i ++){
            //判断
            int can = 1;
            for(int j = 0;j < p; j ++){
                //可以插入,i是值，p是下标
                if( i == res[j] || i - p == res[j] - j ||  i + p == res[j] + j){
                    can = 0;
                    break;
                }
            }
            if(can == 1){
                res[p] = i;
                dfs(res, p+1);
            }
        }
    }

    public static void main(String args[]) {
        List<Integer[]> s = new Fun51().solveNQueens(4);
        for(int i = 0; i < s.size(); i ++){
            for(Integer a : s.get(i)){
                System.out.print(a);
            }
            System.out.println("");
        }
    }
}
