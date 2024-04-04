package site.lrm7.adj.datastructure.queue;

public class TreeNode<E> {
    public E val;
    public TreeNode<E> left;
    public TreeNode<E> right;

    public TreeNode() {
    }

    public TreeNode(E val) {
        this.val = val;
    }

    public TreeNode(E val, TreeNode<E> left, TreeNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
