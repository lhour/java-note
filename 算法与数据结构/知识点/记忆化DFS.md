```java
class Solution {
    int l1, l2, l3;
    String s1, s2, s3;
    
    boolean[][] visited;
    
    public boolean isInterleave(String s1, String s2, String s3) {
        l1 = s1.length();
        l2 = s2.length();
        l3 = s3.length();
        if (l1 + l2 != l3)  return false;
        visited = new boolean[l1 + 1][l2 + 1];
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        return dfs(0, 0, 0);
    }
    private boolean dfs(int i, int j, int k) {
        if (k == l3)    return true;
        if (visited[i][j])  return false;
        visited[i][j] = true;
        
        if (i < l1 && s1.charAt(i) == s3.charAt(k) && dfs(i + 1, j, k + 1)) {
            return true; 
        }           
        if (j < l2 && s2.charAt(j) == s3.charAt(k) && dfs(i, j + 1, k + 1)) {
            return true; 
        }           
        return false;
    }
}
```

[97. 交错字符串 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/interleaving-string/submissions/)

