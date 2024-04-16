package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

import java.util.Stack;

public class Leetcode98 {
    long prev = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode<Integer> node) {
        if(node == null) return true;
        boolean a = isValidBST(node.left);
        if(!a || prev >= node.val) {
            return false;
        }
        prev = node.val;
        return isValidBST(node.right);
    }

    private boolean doIsValidBST(TreeNode<Integer> node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val >= max || node.val <= min) {
            return false;
        }
        boolean a = doIsValidBST(node.left,min, node.val);
        if(!a) {
            return false;
        }
        return doIsValidBST(node.right,node.val, max);
    }

    public boolean isValidBST1(TreeNode<Integer> node) {
        Stack<TreeNode<Integer> > stack = new Stack<>();
        TreeNode<Integer>  p = node;
        long prev = Long.MIN_VALUE;

        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                TreeNode<Integer>  pop = stack.pop();
                if (prev >= pop.val) {
                    return false;
                }
                prev = pop.val;
                p = pop.left;
            }
        }
        return true;
    }
}
