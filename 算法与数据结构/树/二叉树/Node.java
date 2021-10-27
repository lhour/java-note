/*
 * @Author: lhour
 * @Date: 2021-05-24 23:10:20
 * @LastEditTime: 2021-10-14 08:13:53
 * @LastEditors: Please set LastEditors
 * @Description: change me
 * @FilePath: \算法与数据结构\二叉树\Node.java
 * 
 */
package 二叉树;

public class Node<K extends Comparable<K>, V> {
    public K key;
    public V value;
    public Node<K, V> left;
    public Node<K, V> right;

    public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
        /**
         * @description:
         * @param { K:key, V:value, left:nodeleft, right:nodeleft, }
         * @return {Node<K,V>}
         */
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;

    }

}