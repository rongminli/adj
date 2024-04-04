package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode104_3 {
    public int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
            }
            depth++;
        }
        return depth;
    }
}
