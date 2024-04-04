package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode145 {
    public List<Integer> postorderTraversal(TreeNode<Integer>  root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode<Integer> > stack = new LinkedList<>();

        TreeNode<Integer>  curr = root;
        TreeNode<Integer>  pop = null;
        while (!stack.isEmpty() || curr != null) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode<Integer> peek = stack.peek();
                if (peek.right == null) {
                    result.add(peek.val);
                    pop = stack.pop();
                }else if(peek.right == pop) {
                    pop = stack.pop();
                    result.add(pop.val);
                }else{
                    result.add(peek.val);
                    curr = peek.right;
                }
            }
        }
        return result;
    }
}
