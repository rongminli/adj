package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode144 {
    public List<Integer> preorderTraversal(TreeNode<Integer>  root) {
        List<Integer> array = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        TreeNode<Integer>  curr = root;
        while (!stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                array.add(curr.val);
                curr = curr.left;
            }else {
                TreeNode<Integer>  pop = stack.pop();
                array.add(pop.val);
                curr = pop.right;
            }
        }
        return array;
    }
}
