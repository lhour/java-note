$思路及算法$

$\text{Knuth-Morris-Pratt}$ 算法，简称 $\text{KMP}$ 算法，由 $\text{Donald Knuth}、\text{James H. Morris} 和 \text{Vaughan Pratt}$ 三人于 19771977 年联合发表

Knuth-Morris-Pratt 算法的核心为前缀函数，记作 $\pi(i)π(i)$ ，其定义如下：

对于长度为 mm 的字符串 ss，其前缀函数 $\pi(i)(0 \leq i < m)π(i)(0≤i<m)$ 表示 ss 的子串 $s[0:i]s[0:i]$ 的最长的相等的真前缀与真后缀的长度。特别地，如果不存在符合条件的前后缀，那么 $\pi(i) = 0π(i)=0$。其中真前缀与真后缀的定义为不等于自身的的前缀与后缀。

我们举个例子说明：字符串 aabaaabaabaaab 的前缀函数值依次为 0,1,0,1,2,2,30,1,0,1,2,2,3。

$\pi(0) = s0π(0)=0$，因为 aa 没有真前缀和真后缀，根据规定为 00（可以发现对于任意字符串 $\pi(0)=0π(0)=0$ 必定成立）；

$\pi(1) = 1π(1)=1$，因为 aaaa 最长的一对相等的真前后缀为 aa，长度为 11；

$\pi(2) = 0π(2)=0$，因为 aabaab 没有对应真前缀和真后缀，根据规定为 00；

$\pi(3) = 1π(3)=1$，因为 aabaaaba 最长的一对相等的真前后缀为 aa，长度为 11；

$\pi(4) = 2π(4)=2$，因为 aabaaaabaa 最长的一对相等的真前后缀为 aaaa，长度为 22；

$\pi(5) = 2π(5)=2$，因为 aabaaaaabaaa 最长的一对相等的真前后缀为 aaaa，长度为 22；

$\pi(6) = 3π(6)=3，$因为 aabaaabaabaaab 最长的一对相等的真前后缀为 aabaab，长度为 33。

有了前缀函数，我们就可以快速地计算出模式串在主串中的每一次出现。

如何求解前缀函数

长度为 mm 的字符串 ss 的所有前缀函数的求解算法的总时间复杂度是严格 O(m)O(m) 的，且该求解算法是增量算法，即我们可以一边读入字符串，一边求解当前读入位的前缀函数。

为了叙述方便，我们接下来将说明几个前缀函数的性质：

$\pi(i) \leq \pi(i-1) + 1π(i)≤π(i−1)+1。$
依据 $\pi(i)π(i)$ 定义得：$s[0:\pi(i)-1]=s[i-\pi(i)+1:i]s[0:π(i)−1]=s[i−π(i)+1:i]。$
将两区间的右端点同时左移，可得：$s[0:\pi(i)-2] = s[i-\pi(i)+1:i-1]s[0:π(i)−2]=s[i−π(i)+1:i−1]。$
依据 \pi(i-1)π(i−1) 定义得：$\pi(i-1) \geq \pi(i) - 1π(i−1)≥π(i)−1$，即 $\pi(i) \leq \pi(i-1) + 1π(i)≤π(i−1)+1。$
如果 $s[i]=s[\pi(i-1)]s[i]=s[π(i−1)]$，那么 $\pi(i)=\pi(i-1)+1π(i)=π(i−1)+1。$
依据 $\pi(i-1)π(i−1)$ 定义得：$s[0:\pi(i-1)-1]=s[i-\pi(i-1):i-1]s[0:π(i−1)−1]=s[i−π(i−1):i−1]。$
因为 $s[\pi(i-1)]=s[i]s[π(i−1)]=s[i]，$可得 $s[0:\pi(i-1)]=s[i-\pi(i-1):i]s[0:π(i−1)]=s[i−π(i−1):i]$。
依据 $\pi(i)π(i)$ 定义得：$\pi(i)\geq\pi(i-1)+1π(i)≥π(i−1)+1$，结合第一个性质可得 $\pi(i)=\pi(i-1)+1π(i)=π(i−1)+1。$
这样我们可以依据这两个性质提出求解 $\pi(i)π(i)$ 的方案：找到最大的 jj，满足 $s[0:j-1]=s[i-j:i-1]s[0:j−1]=s[i−j:i−1]$，且 $s[i]=s[j]s[i]=s[j]$（这样就有 $s[0:j]=s[i-j:i]s[0:j]=s[i−j:i]$，即 $\pi(i)=j+1π(i)=j+1$）。

注意这里提出了两个要求：

$jj$ 要求尽可能大，且满足 $s[0:j-1]=s[i-j:i-1]s[0:j−1]=s[i−j:i−1]；$
$jj$ 要求满足 $s[i]=s[j]s[i]=s[j]。$
由 $\pi(i-1)π(i−1)$ 定义可知：

$s[0:\pi(i-1)-1]=s[i-\pi(i-1):i-1] \tag{1}$
$s[0:π(i−1)−1]=s[i−π(i−1):i−1$](1)

那么 $j = \pi(i-1)j=π(i−1)$ 符合第一个要求。如果 $s[i]=s[\pi(i-1)]s[i]=s[π(i−1)]$，我们就可以确定 $\pi(i)π(i)。$

否则如果那么 $\pi(i) \leq \pi(i-1)π(i)≤π(i−1)，$因为 $j=\pi(i)-1j=π(i)−1$，所以 $j < \pi(i-1)j < π(i−1)$，于是可以取 (1)(1) 式两子串的长度为 jj 的后缀，它们依然是相等的：$s[\pi(i-1)-j:\pi(i-1)-1]=s[i-j:i-1]s[π(i−1)−j:π(i−1)−1]=s[i−j:i−1]。$

当 $s[i]\neq s[\pi(i-1)]$    $s[i] \ =s[π(i−1)]$ 时，我们可以修改我们的方案为：找到最大的 jj，满足 $s[0:j-1]=s[\pi(i-1)-j:\pi(i-1)-1]s[0:j−1]=s[π(i−1)−j:π(i−1)−1]$，且 $s[i]=s[\pi(i-1)]s[i]=s[π(i−1)]$（这样就有 $s[0:j]=s[\pi(i-1)-j:\pi(i-1)]s[0:j]=s[π(i−1)−j:π(i−1)]$，即 $\pi(i)=\pi(i-1)+1π(i)=π(i−1)+1）$。

注意这里提出了两个要求：

jj 要求尽可能大，且满足 $s[0:j-1]=s[\pi(i-1)-j:\pi(i-1)-1]s[0:j−1]=s[π(i−1)−j:π(i−1)−1]；$
jj 要求满足 $s[i]=s[j]s[i]=s[j]。$
由 $\pi(\pi(i-1)-1)π(π(i−1)−1)$ 定义可知 $j = \pi(\pi(i-1)-1)j=π(π(i−1)−1)$ 符合第一个要求。如果 $s[i]=s[\pi(\pi(i-1)-1)]s[i]=s[π(π(i−1)−1)]$，我们就可以确定 $\pi(i)π(i)。$

此时，我们可以发现 jj 的取值总是被描述为 $\pi(\pi(\pi(\ldots)-1)-1)π(π(π(…)−1)−1)$ 的结构（初始为 $\pi(i-1)π(i−1)$）。于是我们可以描述我们的算法：设定 $\pi(i)=j+1π(i)=j+1$，jj 的初始值为 $\pi(i-1)π(i−1)$。我们只需要不断迭代 $jj$（令 $jj$ 变为 $\pi(j-1)π(j−1)$）直到 $s[i]=s[j]s[i]=s[j]$ 或 $j=0$ 即可，如果最终匹配成功（找到了 jj 使得 $s[i]=s[j]$），那么 $\pi(i)=j+1π(i)=j+1$，否则 $\pi(i)=0π(i)=0。$

复杂度证明

时间复杂度部分，注意到 $\pi(i)\leq \pi(i-1)+1π(i)≤π(i−1)+1$，即每次当前位的前缀函数至多比前一位增加一，每当我们迭代一次，当前位的前缀函数的最大值都会减少。可以发现前缀函数的总减少次数不会超过总增加次数，而总增加次数不会超过 $mm$ 次，因此总减少次数也不会超过 $mm$ 次，即总迭代次数不会超过 mm 次。

空间复杂度部分，我们只用到了长度为 $mm$ 的数组保存前缀函数，以及使用了常数的空间保存了若干变量。



作者：LeetCode-Solution
链接：https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode-solution-ds6y/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。