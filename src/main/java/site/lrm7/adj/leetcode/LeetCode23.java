package site.lrm7.adj.leetcode;
import java.util.Comparator;
import site.lrm7.adj.datastructure.ListNode;
import site.lrm7.adj.datastructure.priorityqueue.Heap;

public class LeetCode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        Comparator<ListNode> cmp = (a,b) -> a.val - b.val;
        Heap<ListNode> heap = new Heap(lists.length, cmp);
        for (ListNode h : lists) {
            if (h != null) {
                heap.offer(h);
            }
        }
        ListNode s = new ListNode(-1, null);
        ListNode p = s;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            p.next = min;
            p = min;
            if (min.next != null)
                heap.offer(min.next);
        }

        return s.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = {
                ListNode.of(-8,-7,-6,-3,-2,-2,0,3),
                ListNode.of(-10,-6,-4,-4,-4,-2,-1,4),
                ListNode.of(-10,-9,-8,-8,-6),
                ListNode.of(-10,0,4),
                null
        };
        ListNode listNode = new LeetCode23().mergeKLists(lists);
        System.out.println(listNode);

    }
}
