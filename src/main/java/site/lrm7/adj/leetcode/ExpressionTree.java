package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.queue.TreeNode;

import java.util.LinkedList;

public class ExpressionTree {
    LinkedList<TreeNode<String>> stack = new LinkedList<>();
    public TreeNode constructExpressionTree(String[] tokens) {
        for (String t : tokens) {
            switch (t) {
                case "+" ,"-" ,"*", "/" -> {
                    TreeNode<String> right = stack.pop();
                    TreeNode<String> left = stack.pop();
                    TreeNode<String> root = new TreeNode<String>(t);
                    root.left = left;
                    root.right = right;
                    stack.push(root);
                }
                default -> {
                    stack.push(new TreeNode<String>(t));
                }
            }
        }
        return stack.peek();
    }
}
