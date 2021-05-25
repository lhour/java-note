package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<K extends Comparable<K>, V> {
    // 记录根节点
    private Node<K, V> root;
    // 记录树中元素个数
    private int size = 0;

    public int size() {
        return this.size;
    }

    private Node<K, V> search(Node<K, V> x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return search(x.right, key);
        } else if (cmp < 0) {
            return search(x.left, key);
        } else {
            return x;
        }
    }

    // 插入
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> x, K key, V value) {
        // 子树为空
        if (x == null) {
            size++;
            return new Node<K, V>(key, value, null, null);
        }

        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else {
            x.value = value;
        }

        return x;
    }

    // 查找
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return x.value;
        }
    }

    public void delete(K key) {
        delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> x, K key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        // 找到要删除的节点
        if (cmp > 0) {
            x.right = delete(x.right, key);
        } else if (cmp < 0) {
            x.left = delete(x.left, key);
        } else {
            size--;
            // 得到右子树中最小节点
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            // 记录最小和次小
            Node<K, V> minNode = x.right;
            Node<K, V> n = x;
            while (minNode.left != null) {
                n = minNode;
                minNode = minNode.left;
            }
            // 删除最小
            n.left = null;

            // minNode的子节点分别指向x的左右节点
            // 指向x的节点指向minNode
            minNode.left = x.left;
            minNode.right = x.right;
            x = minNode;
        }
        return x;
    }

    // 寻找整个树的最小值
    public K min() {
        return min(root).key;
    }

    // 寻找指定树的最小值
    private Node<K, V> min(Node<K, V> x) {
        if (x.left != null) {
            return min(x.left);
        } else {
            return x;
        }
    }

    // 寻找整个树的最大值
    public K max() {
        return max(root).key;
    }

    // 寻找指定树的最大值
    private Node<K, V> max(Node<K, V> x) {
        if (x.right != null) {
            return min(x.right);
        } else {
            return x;
        }
    }

    // 获取整个树的K
    public Queue<K> preErgodic() {
        // 接口无法实例化
        // Queue<K> keys = new Queue<K>();
        Queue<K> keys = new LinkedList<K>();
        preErgodic(root, keys);
        return keys;
    }

    // 获取指定树的Keys
    private void preErgodic(Node<K, V> x, Queue<K> keys) {
        if (x == null) {
            return;
        }
        keys.add(x.key);

        if (x.left != null) {
            preErgodic(x.left, keys);
        }
        if (x.right != null) {
            preErgodic(x.right, keys);
        }
    }

    // 中序
    // 获取整个树的K
    public Queue<K> midErgodic() {
        // 接口无法实例化
        // Queue<K> keys = new Queue<K>();
        Queue<K> keys = new LinkedList<K>();
        midErgodic(root, keys);
        return keys;
    }

    // 获取指定树的Keys
    private void midErgodic(Node<K, V> x, Queue<K> keys) {
        if (x == null) {
            return;
        }
        if (x.left != null) {
            midErgodic(x.left, keys);
        }
        keys.add(x.key);
        if (x.right != null) {
            midErgodic(x.right, keys);
        }
    }

    // 后序
    public Queue<K> afterErgodic() {
        // 接口无法实例化
        // Queue<K> keys = new Queue<K>();
        Queue<K> keys = new LinkedList<K>();
        afterErgodic(root, keys);
        return keys;
    }

    // 获取指定树的Keys
    private void afterErgodic(Node<K, V> x, Queue<K> keys) {
        if (x == null) {
            return;
        }
        if (x.left != null) {
            afterErgodic(x.left, keys);
        }
        if (x.right != null) {
            afterErgodic(x.right, keys);
        }
        keys.add(x.key);
    }
}
