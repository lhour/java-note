package leetcode;

import java.util.Arrays;

//解数独
public class Fun37 {
    /**
     * 数组就像一个图，key是下标，value是data
     */

    private static boolean[][] row = new boolean[9][9];
    private static boolean[][] col = new boolean[9][9];
    private static boolean[][][] ur = new boolean[3][3][9];
    // 用一个新的板来储存答案，如果有多解，就要改成list，可以添加多解
    private static char[][] board2 = new char[9][9];

    public static void solveSudoku(char[][] board) {
        for (int m = 0; m < 9; m++) {
            for (int n = 0; n < 9; n++) {
                if (board[m][n] != '.') {
                    int i = board[m][n] - '0' - 1;
                    row[m][i] = col[n][i] = ur[m / 3][n / 3][i] = true;
                }
            }
        }
        dfs(board, 0);
        // 二维数组没有直接复制的方法，真是巨坑
        for (int i = 0; i < 9; i++) {
            board[i] = Arrays.copyOf(board2[i], 9);
        }
    }

    public static void dfs(char[][] board, int p) {

        // 计算出坐标
        int x = p / 9;
        int y = p % 9;

        // 当填到最后时，可以结束
        if (p == 81) {
            for (int i = 0; i < 9; i++) {
                board2[i] = Arrays.copyOf(board[i], 9);
            }
            return;
        }
        // 是否已经填上了填数，是的话就直接下一个
        if (board[x][y] != '.') {
            dfs(board, p + 1);
        } else {
            // 1-9挨个试
            for (int i = 0; i < 9; i++) {
                if (row[x][i] != true && col[y][i] != true && ur[x / 3][y / 3][i] != true) {
                    // 先填上
                    row[x][i] = col[y][i] = ur[x / 3][y / 3][i] = true;
                    board[x][y] = (char) (i + '0' + 1);
                    // 下一个
                    dfs(board, p + 1);
                    // 当回溯到这里时，要注意将其删去，也就是弹出
                    row[x][i] = col[y][i] = ur[x / 3][y / 3][i] = false;
                    board[x][y] = '.';
                }
            }

        }
    }

    public static void main(String args[]) {
        char[][] board = { 
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, 
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, 
                { '4', '.', '.', '8', '.', '3', '.', '.', '.' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '.' }, 
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, 
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };

        char[][] board3 = { 
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, 
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, 
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, 
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, 
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, };

        solveSudoku(board);

        for (char[] s : board) {
            for (char s1 : s) {
                System.out.print(s1 + " ");
            }
            System.out.println("\n");
        }

    }
}
