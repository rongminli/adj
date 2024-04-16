package site.lrm7.adj.datastructure.binarysearchtree;

import junit.framework.TestCase;

public class BSTTree1Test extends TestCase {

    public BSTTree1 createTree() {
        BSTTree1.BSTNode n1 = new BSTTree1.BSTNode(1, "张无忌");
        BSTTree1.BSTNode n3 = new BSTTree1.BSTNode(3, "宋青书");
        BSTTree1.BSTNode n2 = new BSTTree1.BSTNode(2, "周芷若", n1, n3);

        BSTTree1.BSTNode n5 = new BSTTree1.BSTNode(5, "说不得");
        BSTTree1.BSTNode n7 = new BSTTree1.BSTNode(7, "盈利");
        BSTTree1.BSTNode n6 = new BSTTree1.BSTNode(6, "赵敏", n5, n7);
        BSTTree1.BSTNode root = new BSTTree1.BSTNode(4, "小昭", n2, n6);

        BSTTree1 tree = new BSTTree1();
        tree.root = root;
        return tree;
    }

    public void testGet() {
        BSTTree1 tree = createTree();

        assertEquals("张无忌", tree.get(1));
        assertEquals("说不得", tree.get(5));
        assertEquals("赵敏", tree.get(6));
        assertEquals("小昭", tree.get(4));
        assertEquals("宋青书", tree.get(3));
        assertNull(tree.get(10));
    }
}