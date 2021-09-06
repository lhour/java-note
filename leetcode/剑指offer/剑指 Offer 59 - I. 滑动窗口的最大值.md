给定一个数组 `nums` 和滑动窗口的大小 `k`，请找出所有滑动窗口里的最大值。

**示例:**

```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值

---------------               -----

[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```


提示：

你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



**知识点**

`大根堆/小根堆/优先队列`

[PriorityQueue (Java Platform SE 8 ) (oracle.com)](https://docs.oracle.com/javase/8/docs/api/index.html)

lambda表达式



```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        int n = nums.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
        	(pair1, pair2) -> {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        );
        
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}

```

PriorityQueue常用api

| Modifier and Type | Method             | Description                                                  |
| :---------------- | :----------------- | ------------------------------------------------------------ |
| `boolean`         | `remove(Object o)` | Removes a single instance of the specified element from this queue, if it is present. |
| `boolean`         | `offer(E e)`       | Inserts the specified element into this queue .              |
| `E`               | `peek()`           | Retrieves, but does not remove, the head of this queue, or returns `null` if this queue is empty. |
| `E`               | `poll()`           | Retrieves and removes the head of this queue, or returns `null` if this queue is empty. |

