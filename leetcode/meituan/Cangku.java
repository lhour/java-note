package meituan;

import java.util.*;
import java.io.*;
public class Cangku {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n + 1];
        int[] order = new int[n + 1];
        for (int i = 1; i <= n; i++) arr[i] = in.nextInt();
        for (int i = 1; i <= n; i++) order[i] = in.nextInt();
        UnionFind unionFind = new UnionFind(n + 1);
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int[] ans = new int[n];
        int idx = n - 1;
        boolean[] seen = new boolean[n + 3];
        for (int i = n; i >= 1; i--){
            // 添加答案
            ans[idx --] = max;
            // 当前点下标
            int now = order[i];
            // 与左边节点合并
            if(seen[now - 1]){
                int key = unionFind.find(now - 1);
                map.put(key, map.get(key) + arr[now]);
                max = Math.max(max, map.get(key));
                unionFind.unionSet(now - 1, now);
            }
            // 与右边节点合并
            if(seen[now + 1]){
                int key_prev = unionFind.find(now);
                if(key_prev == now){
                    unionFind.unionSet(now + 1, now);
                    int key = unionFind.find(now);
                    map.put(key, map.get(key) + arr[now]);
                    max = Math.max(max, map.get(key));
                }else{
                    int key_back = unionFind.find(now + 1);
                    unionFind.unionSet(now + 1, now);
                    int key = unionFind.find(now);
                    if(key == key_prev){
                        int sum = map.get(key_back);
                        map.remove(key_back);
                        map.put(key, map.get(key)  + sum);
                        max = Math.max(max, map.get(key));
                    }else{
                        int sum = map.get(key_prev);
                        map.remove(key_prev);
                        map.put(key, map.get(key) + sum);
                        max = Math.max(max, map.get(key));
                    }
                }
            }
            // 孤立的一个节点，单独加入map，后面的点将会以该点为parent合并并查集
            if(!seen[now + 1] && !seen[now + 1]){
                map.put(now, arr[now]);
                max = Math.max(max, arr[now]);
            }
            seen[now] = true;
        }
        for (int i = 0; i < n; i++){
            System.out.println(ans[i]);
        }
    }
}

class UnionFind {

    int[] parent;
    int[] rank; 
    int n;
    int setCount;

    public UnionFind(int n) {
        this.n = n;
        this.setCount = n;
        this.parent = new int[n];
        this.rank = new int[n];
        Arrays.fill(rank, 1);
        for(int i=0; i<n; i++) parent[i] = i;
    }

    public int find(int index) {
        if (parent[index] != index) {
            parent[index] = this.find(parent[index]);
        }
        return parent[index];
    }

    public boolean unionSet(int x, int y) {
        int x_root = this.find(x);
        int y_root = this.find(y);
        if (x_root == y_root) {
            return false;
        }
        if (rank[x_root] > rank[y_root]) {
            parent[y_root] = x_root;
        } else if (rank[x_root] < rank[y_root]) {
            parent[x_root] = y_root;
        } else {
            rank[x_root]++;
            parent[y_root] = x_root;
        }
        this.setCount--;
        return true;
    }

    public int getSetCount() {
        return this.setCount;
    }
}
