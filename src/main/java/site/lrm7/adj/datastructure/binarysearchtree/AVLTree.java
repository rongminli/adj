package site.lrm7.adj.datastructure.binarysearchtree;

public class AVLTree {
    private AVLNode root;

    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1;

        public AVLNode(int key, Object value) {
            this.value = value;
            this.key = key;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // 更新节点高度
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right));
    }

    // 平衡因子 balance factor = 左子树高度 - 右子树高度

    /**
     * @param node 需要旋转的节点
     * @return 小于 -1 右边较高
     * 大于 1 左边较高
     */
    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }

    private AVLNode rightRotate(AVLNode node) {
        AVLNode left = node.left;
        AVLNode leftRight = left.right;
        left.right = node;
        node.left = leftRight;
        updateHeight(node);
        updateHeight(leftRight);
        return left;
    }

    private AVLNode leftRotate(AVLNode node) {
        AVLNode right = node.right;
        AVLNode rightLeft = right.left;
        right.left = node;
        node.right = rightLeft;
        updateHeight(node);
        updateHeight(rightLeft);
        return right;
    }

    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    private AVLNode balance(AVLNode node) {
        if (node == null) return null;
        int bf = bf(node);
        if (bf < -1) { // 右边更高，需要左旋
            return bf(node.right) > 0 ? leftRightRotate(node.right) : leftRotate(node);
        } else if (bf > 1) { // 左边更高，需要右旋
            return bf(node.left) < 0 ? rightLeftRotate(node.left) : rightRotate(node);
        }
        return node;
    }

    public void put(int key, Object value) {
        doPut(root, key, value);
        root = balance(root);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        if (node == null) return new AVLNode(key, value);

        if (key == node.key) {
            node.value = value;
            return node;
        }
        if (node.key < key) {
            node.right = doPut(node.right, key, value);
        } else {
            node.left = doPut(node.left, key, value);
        }
        updateHeight(node);
        return balance(node);
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        if (node == null) return null;

        if (key < node.key) {
            node.left = doRemove(node.left,key);
        }else if(key > node.key) {
            node.right = doRemove(node.right, key);
        }else {
            if(node.left == null && node.right == null) {
                return null;
            }else if (node.left == null) {
                node = node.right;
            }else  if ( node.right == null) {
                node = node.left;
            }else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }

        updateHeight(node);
        return balance(node);

    }
}
