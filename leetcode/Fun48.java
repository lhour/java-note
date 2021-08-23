package leetcode刷题;

public class Fun48 {
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        if(length <= 1){
            return;
        }
        for(int layer = 0;layer < length/2;layer ++){
            for(int num = 0;num < length-1-2*layer;num ++){
                int x = 0 + layer;
                int y = num + layer;
                int t1 = matrix[x][y];
                int t2 = 0;
                for(int i = 0;i <= 4;i ++){
                    t2 = matrix[y][length-x-1];
                    matrix[y][length-x-1] = t1;
                    int z = x;
                    x = y;
                    y = length-z-1;
                    t1 = t2;
                }
            }
        }
        return;
    }

    public static void main(String args[]) {
        int[][] pro = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{12,13,14,15}};
        rotate(pro);
        for(int[] p:pro){
            for(int n:p){
                System.out.print(n+" ");
            }
            System.out.println("");
        }
    }
}