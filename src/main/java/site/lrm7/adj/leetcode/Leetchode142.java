package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.ListNode;

public class Leetchode142 {
    public ListNode detectCycle(ListNode head) {
        ListNode h = head;
        ListNode t = head;
        while (h != null && h.next != null) {
            h = h.next.next;
            t = t.next;
            if (h == t) {
                t = head;
                while(true) {
                    if (t == h) {
                        return t;
                    }
                    t = t.next;
                    h = h.next;
                }
            }
        }

        return null;
    }
}
