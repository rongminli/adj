package site.lrm7.adj.datastructure.binarysearchtree;

/**
 * 红黑树的特性
 * 1.所有节点都有两种颜色：红色核黑色
 * 2.所有null视为黑色
 * 3.红色节点不能相邻
 * 4.根节点是黑色
 * 5.从根到任意一个叶子节点，路径中的黑色节点数一样（黑色完美平衡）
 */
public class RedBlackTree {
    enum Color {
        RED, BLACK;
    }

    private Node root;

    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;
        Color color = Color.RED;

        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        Node uncle() {
            if (parent == null || parent.parent == null) return null;
            return parent.isLeftChild() ? parent.parent.right : parent.parent.left;
        }

        Node sibling() {
            if (parent == null) return null;
            return isLeftChild() ? right : left;
        }

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    boolean isBlack(Node node) {
        return !isRed(node);
    }

    private void rightRotate(Node pink) {
        Node yellow = pink.left;
        Node green = yellow.right;
        Node pinkParent = pink.parent;
        if (green != null) {
            green.parent = pink;
        }
        yellow.right = pink;
        yellow.parent = pinkParent;
        pink.left = green;
        pink.parent = yellow;
        if (pinkParent == null) {
            root = yellow;
        } else if (pinkParent.left == pink) {
            pinkParent.left = yellow;
        } else {
            pinkParent.right = yellow;
        }
    }

    private void leftRotate(Node node) {
    }

    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }
        Node inserted = new Node(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);
    }

    /**
     * 1.插入节点是根节点，将根节点变黑
     * 2.插入点的父亲为黑色，树的红黑性质不变，无需调整
     * 3.插入点的父亲为红色，不平衡
     * 3.1 叔叔为红色
     * 3.2 叔叔为黑色
     *
     * @param x
     */
    void fixRedRed(Node x) {
        if (x == root) {
            x.color = Color.BLACK;
            return;
        }
        if (isBlack(x.parent)) {
            return;
        }
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandparent.color = Color.RED;
            fixRedRed(grandparent);
            return;
        } else {
            if (parent.isLeftChild() && x.isLeftChild()) {
                parent.color = Color.BLACK;
                grandparent.color = Color.RED;
                rightRotate(grandparent);
            } else if (parent.isLeftChild() && !x.isLeftChild()) {
                leftRotate(parent);
                x.color = Color.BLACK;
                grandparent.color = Color.RED;
                rightRotate(grandparent);
            } else if (!parent.isLeftChild() && !x.isLeftChild()) {
                parent.color = Color.BLACK;
                grandparent.color = Color.RED;
                leftRotate(grandparent);
            } else {
                rightRotate(parent);
                x.color = Color.BLACK;
                grandparent.color = Color.RED;
                leftRotate(grandparent);
            }
        }
    }


    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    // 处理双黑
    private void fixDoubleBlack(Node x) {
        if (x == root) {
            return;
        }
        Node parent = x.parent;
        Node sibling = x.sibling();
        if (isBlack(sibling)) { // 进入case3
            if (x.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            fixDoubleBlack(x);
            return;
        }
        // 兄弟是黑色,且两个侄子也是黑色。
        if (sibling != null) {
            // case 4 兄弟是黑色，两个侄子也是黑色。
            if (isBlack(sibling.left) && isBlack(sibling.right) ) {
                sibling.color = Color.RED;
                if (isRed(parent)) {
                    parent.color = Color.BLACK;
                }else {
                    fixDoubleBlack(parent);
                }
            }
            // case 5 兄弟是黑色，侄子是红色
            else {
                // LL
                if (sibling.isLeftChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = Color.BLACK;
                    sibling.color = parent.color;
                }
                // LR
                else if(sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                parent.color = Color.BLACK;
            }
        }else {
            fixDoubleBlack(parent);
        }

    }

    private void doRemove(Node deleted) {
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        if (replaced == null) {
            if (deleted == root) {
                root = null;
            } else {
                if (isBlack(deleted)) {
                    fixDoubleBlack(deleted);
                } else {
                    // 无需处理
                }
                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        if (deleted.left == null || deleted.right == null) {
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                root.left = root.right = null;
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;
                if (isBlack(deleted) && isBlack(replaced)) {
                    fixDoubleBlack(replaced);
                } else {
                    replaced.color = Color.BLACK;
                }
            }
            return;
        }
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        doRemove(replaced);
    }

    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    private Node findReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }
}
