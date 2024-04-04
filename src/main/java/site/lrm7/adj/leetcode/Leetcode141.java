package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.ListNode;

public class Leetcode141 {

    public boolean hasCycle(ListNode head) {
        ListNode h = head;
        ListNode t = head;
        while (h != null && h.next != null) {
            h = h.next.next;
            t = t.next;
            if (h == t) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        ListNode listNode = ListNode.of(1, 2, 3, 4, 5, 6, 7, 8);
    }
}
