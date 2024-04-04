package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

/**
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 */
public class Leetcode104_1 {
    public int maxDepth(TreeNode node) {
        if(node == null) {
            return 0;
        }
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Integer.max(right, left) + 1;
    }
}
