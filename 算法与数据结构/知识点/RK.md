$Rabin-Karp$ 字符串编码是一种将字符串映射成整数的编码方式，可以看成是一种哈希算法。具体地，假设字符串包含的字符种类不超过 $|\Sigma|∣Σ∣$（其中 $\SigmaΣ$ 表示字符集），那么我们选一个大于等于 $|\Sigma|∣Σ∣$ 的整数 $\textit{base}$，就可以将字符串看成 $\textit{base}$ 进制的整数，将其转换成十进制数后，就得到了字符串对应的编码。



```java
class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        long prefix = 0, suffix = 0;
        long base = 31, mod = 1000000007, mul = 1;
        int happy = 0;
        for (int i = 1; i < n; ++i) {
            prefix = (prefix * base + (s.charAt(i - 1) - 'a')) % mod;
            suffix = (suffix + (s.charAt(n - i) - 'a') * mul) % mod;
            if (prefix == suffix) {
                happy = i;
            }
            mul = mul * base % mod;
        }
        return s.substring(0, happy);
    }
}

/*
* 作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/longest-happy-prefix/solution/zui-chang-kuai-le-qian-zhui-by-leetcode-solution/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/
```

