package site.lrm7.adj.datastructure.binarysearchtree;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


public class BSTTree2Test extends TestCase {

    public BSTTree2 createTree() {
        BSTTree2.BSTNode n1 = new BSTTree2.BSTNode(1, "张无忌");
        BSTTree2.BSTNode n3 = new BSTTree2.BSTNode(3, "宋青书");
        BSTTree2.BSTNode n2 = new BSTTree2.BSTNode(2, "周芷若", n1, n3);

        BSTTree2.BSTNode n5 = new BSTTree2.BSTNode(5, "说不得");
        BSTTree2.BSTNode n7 = new BSTTree2.BSTNode(7, "盈利");
        BSTTree2.BSTNode n6 = new BSTTree2.BSTNode(6, "赵敏", n5, n7);
        BSTTree2.BSTNode root = new BSTTree2.BSTNode(4, "小昭", n2, n6);

        BSTTree2<Integer, String> tree = new BSTTree2<>();
        tree.root = root;
        return tree;
    }

    public void testGet1() {
        BSTTree2<Integer, String> tree = createTree();

        assertEquals("张无忌", tree.get(1));
        assertEquals("说不得", tree.get(5));
        assertEquals("赵敏", tree.get(6));
        assertEquals("小昭", tree.get(4));
        assertEquals("宋青书", tree.get(3));
        assertNull(tree.get(10));
    }

    public void testMin() {
        BSTTree2<Integer, String> tree = createTree();
        assertEquals("张无忌", tree.min());
    }

    public void testMax() {
        BSTTree2<Integer, String> tree = createTree();
        assertEquals("盈利", tree.max());
    }

    public void testPut() {
        BSTTree2<Integer, Object> tree = new BSTTree2();
        tree.put(4, new Object());
        tree.put(2, new Object());
        tree.put(1, new Object());
        tree.put(6, new Object());
        tree.put(3, new Object());
        tree.put(5, new Object());
        tree.put(7, new Object());
        assertTrue(isSameTree(createTree().root, tree.root));
    }

    private Boolean isSameTree(BSTTree2.BSTNode tree1, BSTTree2.BSTNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        if (tree1.key != tree2.key) {
            return false;
        }
        return isSameTree(tree1.left, tree2.left)
                && isSameTree(tree1.right, tree2.right);
    }

    public void testPredecessor() {
        BSTTree2<Integer, String> tree = createTree();
        assertEquals("说不得", tree.predecessor(6));
        assertNull(tree.predecessor(1));
        assertEquals("宋青书", tree.predecessor(4));
        assertEquals("小昭", tree.predecessor(5));
    }

    public void testLess() {
        BSTTree2<Integer, String> tree = createTree();
        assertIterableEquals(List.of("张无忌", "周芷若", "宋青书", "小昭", "说不得"), tree.less(6));
    }

    public void testGreater() {
        BSTTree2<Integer, String> tree = createTree();
        assertIterableEquals(List.of("盈利"), tree.greater(6));
    }
}