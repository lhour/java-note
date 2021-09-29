![image.png](树形DP.assets/1600753755-xhouuB-image.png)

```typescript
const minCameraCover = (root) => {
  // 以root为根节点的子树，放置的最少相机数
  const minCam = (root, placeCam, watched) => {
    if (root == null) {  // 遍历到null节点
      if (placeCam) {    // 父节点问自己有相机的情况，但臣妾办不到
        return Infinity; // 返回一个无穷大，让这个返回值失效
      } else {
        return 0;
      }
    }
    if (placeCam) {        // root放置相机
      return 1 + Math.min( // root放了相机，相机数有 1 保底
        minCam(root.left, false, true) + minCam(root.right, false, true), 
        minCam(root.left, true, true) + minCam(root.right, false, true), 
        minCam(root.left, false, true) + minCam(root.right, true, true) 
      );  
    } else {
      if (watched) { // root没放相机，但被父亲监控了
        return Math.min(
          minCam(root.left, true, true) + minCam(root.right, true, true),
          minCam(root.left, true, true) + minCam(root.right, false, false), 
          minCam(root.left, false, false) + minCam(root.right, true, true), 
          minCam(root.left, false, false) + minCam(root.right, false, false) 
        );
      } else {      // root没有相机，也没被父亲监控，被儿子监控了
        return Math.min(
          minCam(root.left, true, true) + minCam(root.right, true, true), 
          minCam(root.left, true, true) + minCam(root.right, false, false), 
          minCam(root.left, false, false) + minCam(root.right, true, true) 
        );
      }
    }
  };
  return Math.min(
    minCam(root, true, true),  // 根节点有相机
    minCam(root, false, false) // 根节点没有相机，因为没有父亲，没有被父亲监控，是被儿子监控
  );
};

作者：xiao_ben_zhu
链接：https://leetcode-cn.com/problems/binary-tree-cameras/solution/shou-hua-tu-jie-cong-di-gui-you-hua-dao-dong-tai-g/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```

