package site.lrm7.adj.datastructure.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BSTTree2<K extends Comparable<K>, V> {

    BSTNode<K, V> root;

    static class BSTNode<T, V> {
        T key;
        V value;
        BSTNode<T, V> left;
        BSTNode<T, V> right;

        public BSTNode(T key) {
            this.key = key;
        }

        public BSTNode(T key, V value, BSTNode<T, V> left, BSTNode<T, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;

        }

        public BSTNode(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public V get(K key) {
        BSTNode<K, V> node = root;
        while (node != null) {
            if (node.key == key) return node.value;
            node = key.compareTo(node.key) > 0 ? node.right : node.left;
        }
        return null;
    }

    public V get1(K key) {
        return doGet(root, key);
    }

    private V doGet(BSTNode<K, V> node, K key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0) return doGet(node.left, key);
        else if (key == node.key) return node.value;
        else return doGet(node.right, key);
    }

    public V min() {
        return min(root);
    }

    private V min(BSTNode<K, V> node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    public V max() {
        return max(root);
    }

    private V max(BSTNode<K, V> node) {
        if (node == null) return null;
        while (node.right != null) {
            node = node.right;
        }
        return node.value;
    }

    public void put(K key, V value) {
        BSTNode<K, V> node = root;
        BSTNode<K, V> parent = null;

        while (node != null) {
            if (node.key == key) node.value = value;
            parent = node;
            node = key.compareTo(node.key) > 0 ? node.right : node.left;
        }

        BSTNode<K, V> newNode = new BSTNode<>(key, value);
        if (parent == null) root = newNode;
        else if (key.compareTo(parent.key) > 0) parent.right = newNode;
        else parent.left = newNode;
    }

    public V predecessor(K key) {
        BSTNode<K, V> node = root;
        BSTNode<K, V> ancestorFromLeft = null;

        while (node != null) {
            if (node.key == key) {
                break;
            }
            if (key.compareTo(node.key) > 0) {
                ancestorFromLeft = node;
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (node == null) {
            return null;
        }

        if (node.left != null) {
            return max(node.left);
        }

        if (ancestorFromLeft == null) return null;
        return ancestorFromLeft.value;
    }

    public V successor(K key) {
        BSTNode<K, V> node = root;
        BSTNode<K, V> ancestorFromRight = null;

        while (node != null) {
            if (node.key == key) {
                break;
            }
            if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                ancestorFromRight = node;
                node = node.left;
            }
        }

        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return min(node.right);
        }

        if (ancestorFromRight == null) return null;
        return ancestorFromRight.value;
    }

    public V delete(K key) {
        BSTNode<K, V> node = root;
        BSTNode<K, V> parent = null;

        while (node != null) {
            if (node.key == key) {
                break;
            }
            parent = node;
            if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if (node == null) return null;
        if (node.left == null) {
            shift(parent, node, node.right);
        } else if (node.right == null) {
            shift(parent, node, node.left);
        } else {
            BSTNode<K, V> s = node.right;
            BSTNode<K, V> sParent = node;
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            if (sParent != node) {
                shift(sParent, s, s.right);
                s.right = node.right;
            }
            shift(parent, node, s);
            s.left = node.left;
        }
        return node.value;
    }

    private void shift(BSTNode<K, V> parent, BSTNode<K, V> deleted, BSTNode<K, V> child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    public List<V> less(K key) {
        ArrayList<V> result = new ArrayList<>();
        Stack<BSTNode<K, V>> stack = new Stack<>();
        BSTNode<K, V> p = root;

        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (pop.key.compareTo(key) < 0) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    public List<V> greater(K key) {
        ArrayList<V> result = new ArrayList<>();
        Stack<BSTNode<K, V>> stack = new Stack<>();
        BSTNode<K, V> p = root;

        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (pop.key.compareTo(key) > 0) {
                    result.add(pop.value);
                }else {
                    break;
                }
                p = pop.left;
            }
        }
        return result;
    }

    public List<V> between(K key1, K key2) {
        ArrayList<V> result = new ArrayList<>();
        Stack<BSTNode<K, V>> stack = new Stack<>();
        BSTNode<K, V> p = root;

        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode<K, V> pop = stack.pop();
                if (pop.key.compareTo(key1) > 0
                        && pop.key.compareTo(key2) <= 0) {
                    result.add(pop.value);
                }else if(pop.key.compareTo(key2) >0) {
                    break;
                }
                p = pop.right;
            }
        }

        return result;
    }
}
