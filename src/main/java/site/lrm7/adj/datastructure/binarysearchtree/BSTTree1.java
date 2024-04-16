package site.lrm7.adj.datastructure.binarysearchtree;

public class BSTTree1 {

    BSTNode root;

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;

        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public Object get(int key) {
        BSTNode node = root;
        while (node != null) {
            if(node.key == key) return node.value;
            node = key > node.key ? node.right : node.left;
        }
        return null;
    }

    private Object doGet(BSTNode node, int key) {
        if (node == null)           return null;
        if (key < node.key)         return doGet(node.left, key);
        else if(key == node.key)    return node.value;
        else                        return doGet(node.right, key);
    }

    public Object min() {
        BSTNode node = root;
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
