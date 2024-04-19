package site.lrm7.adj.datastructure.twothreetree;

import java.util.Arrays;

public class BTree {

    static class Node {
        int[] keys; // 关键字
        Node[] children; // 子节点
        int keyNumber; // 关键字数量
        boolean leaf; // 是否是叶子节点
        int t; // 最小度数

        protected Node(int t) {
            this.t = t;
            keys = new int[2 * t - 1];
            children = new Node[2 * t];
            keyNumber = 0;
            leaf = true;
        }

        public Node(int[] ints) {
            keys = ints;
            leaf = true;
        }

        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        protected Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            if (leaf) {
                return null;
            }
            return children[i].get(key);
        }

        // 向 keys 指定索引 index 处插入 key
        protected void insertKey(int key, int index) {
            // 使用 System.arraycopy 来移动数组元素，为新键值腾出空间
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            // 在指定索引处插入新键值
            keys[index] = key;
            // 键值数量增加
            keyNumber++;
        }

        // 向 children 指定索引 index 处插入 child
        protected void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }

        int removeKey(int index) {
            int t = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return t;
        }

        int removeLeftmostKey() {
            return removeKey(0);
        }

        int removeRightmostKey() {
            return removeKey(keyNumber - 1);
        }

        Node removeChild(int index) {
            Node t = children[index];
            System.arraycopy(children, index + 1, children, index, --keyNumber - index);
            return t;
        }

        Node removeLeftmostChild() {
            return removeChild(0);
        }

        Node removeRightmostChild() {
            return removeChild(keyNumber);
        }

        Node childLeftSibing(int index) {
            return index == 0 ? null : children[index - 1];
        }

        Node childRightSibing(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

        void moveToTarget(Node target) {
            int start = target.keyNumber;
            System.arraycopy(keys, 0, target.keys, start, keyNumber);
            if (!leaf) {
                System.arraycopy(children, 0, target.children, start, keyNumber + 1);
            }
            target.keyNumber = keyNumber;
        }
    }

    Node root;

    int t; // 树中节点的最小度数
    final int MIN_KEY_NUMBER; // 节点的最小关键字数量
    final int MAX_KEY_NUMBER; // 节点的最大关键字数量

    public BTree() {
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MIN_KEY_NUMBER = t - 1;
        MAX_KEY_NUMBER = 2 * t - 1;
    }

    // 1. 是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    // 2. 插入
    public void put(int key) {
        doPut(root, key, null, 0);
    }

    private void doPut(Node node, int key, Node parent, int index) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] == key) {
                return; // 更新
            }
            if (node.keys[i] > key) {
                break; // 找到了插入位置
            }
            i++;
        }
        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            Node child = node.children[i];
            doPut(child, key, node, i);
        }
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    public void split(Node left, Node parent, int index) {
        if (parent == null) {
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.children[0] = left;
            this.root = newRoot;
            parent = newRoot;
        }
        Node right = new Node(t);
        right.leaf = left.leaf;
        int x = left.keys[t - 1];
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        if (!left.leaf) {
            System.arraycopy(left.children, t, right.children, 0, t);
        }
        left.keyNumber = t - 1;
        right.keyNumber = t - 1;
        parent.insertKey(x, index);
        parent.insertChild(right, index + 1);
    }

    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    private void doRemove(Node parent, Node node, int index, int key) {
        int i = 0;
        while (i < node.keyNumber) {
            if (node.keys[i] >= key) {
                break;
            }
            i++;
        }
        // i 找到： 代表删除key的索引
        // i 没找到： 代表第i个孩子继续查找
        if (node.leaf) {
            if (!found(node, key, i)) {
                return;
            } else {
                node.removeKey(i);
            }
        }
        // 非叶子节点
        else {
            if (!found(node, key, i)) { // case3
                doRemove(node, node.children[i], i, key);
            } else { // case4
                Node s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                int skey = s.keys[0];
                node.keys[i] = skey;
                doRemove(node, node.children[i + 1], i+1, skey);
            }
        }
        if (node.keyNumber < MIN_KEY_NUMBER) {
            // 调整平衡 case5 case6
            balance(parent, node, index);
        }
    }

    private void balance(Node parent, Node x, int i) {
        if (x == root) {
            if(root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        Node left = parent.childLeftSibing(i);
        Node right = parent.childRightSibing(i);
        if (left != null && left.keyNumber > MIN_KEY_NUMBER) { // case 5.1
            x.insertKey(parent.keys[i - 1], 0);
            if (!left.leaf) {
                x.insertChild(left.removeRightmostChild(), 0);
            }
            parent.keys[i - 1] = left.removeRightmostKey();
            return;
        }
        if (right != null && right.keyNumber > MIN_KEY_NUMBER) { // case 5.2
            x.insertKey(parent.keys[i], x.keyNumber);
            if (!right.leaf) {
                x.insertChild(right.removeLeftmostChild(), x.keyNumber + 1);
            }
            parent.keys[i] = right.removeLeftmostKey();
            return;
        }
        // case 5.3
        if (left != null) {
            // 向左兄弟合并
            parent.removeChild(i);
            left.insertKey(parent.removeKey(i - 1), left.keyNumber);
            x.moveToTarget(left);
        } else {
            // 向右兄弟合并
            parent.removeChild(i + 1);
            x.insertKey(parent.removeKey(i), x.keyNumber);
            right.moveToTarget(x);
        }
    }

    private boolean found(Node node, int key, int i) {
        return node.keys[i] == key;
    }

}
