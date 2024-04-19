package site.lrm7.adj.datastructure.twothreetree;

import junit.framework.TestCase;

public class BTreeTest extends TestCase {
    public void testsplit() {
        /*
                5             2|5
              /   \    ==>  /  |  \
            1|2|3  6        1   3    6
         */
        BTree tree = new BTree();
        BTree.Node root = tree.root;
        root.leaf = false;
        root.keys[0] = 5;
        root.keyNumber = 1;

        root.children[0] = new BTree.Node(new int[]{1,2,3});
        root.children[0].keyNumber = 3;

        root.children[1] = new BTree.Node(new int[]{6});
        root.children[1].keyNumber = 1;

        tree.split(root.children[0], root, 0);
        assertEquals("[2, 5]", root.toString());
        assertEquals("[1]", root.children[0].toString());
        assertEquals("[3]", root.children[1].toString());
        assertEquals("[6]", root.children[2].toString());

    }

    public void testRemove() {
      BTree bTree = new BTree(3); // 创建一个最小度数为3的B树
      // 插入一些键值
      bTree.put(10);
      bTree.put(20);
      bTree.put(5);
      bTree.put(6);
      bTree.put(12);
      bTree.put(30);
      bTree.put(7);
      bTree.put(17);

      // 确认键值存在
      assertTrue(bTree.contains(6));
      assertTrue(bTree.contains(12));
      assertTrue(bTree.contains(7));

      // 删除键值
      bTree.remove(6);
      bTree.remove(12);
      bTree.remove(7);

      // 确认键值已被删除
      assertFalse(bTree.contains(6));
      assertFalse(bTree.contains(12));
      assertFalse(bTree.contains(7));
  }
}