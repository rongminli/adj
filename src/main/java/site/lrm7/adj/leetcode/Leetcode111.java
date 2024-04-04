package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode111 {
    public int minDepth(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                depth++;
                TreeNode curr = queue.poll();
                if(curr.left == null && curr.right == null) {
                    return depth;
                }
                if(curr.left != null) {
                    queue.offer(curr.left);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return depth;
    }
}
