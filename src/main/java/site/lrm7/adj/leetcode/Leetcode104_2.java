package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

import java.util.LinkedList;

public class Leetcode104_2 {
    public int maxDepth(TreeNode root) {
        TreeNode curr = root;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int max = 0;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    int size = stack.size();
                    if(size > max) max = size;
                    pop = stack.pop();
                }else {
                    curr = peek.right;
                }

            }
        }

        return max;
    }
}
