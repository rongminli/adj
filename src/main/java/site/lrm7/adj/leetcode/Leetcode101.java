package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 */
public class Leetcode101 {
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }
    private boolean check(TreeNode left, TreeNode right){
        if(left == null && right == null) {
            return true;
        }
        if(left ==null || right == null) {
            return false;
        }
        if(left.val == right.val) {
            return check(left.right, right.left)
                    && check(left.left, right.right);
        }else {
            return false;
        }
    }
}
