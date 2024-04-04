package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.ListNode;

public class Leetcode23 {
    public ListNode margeKLists(ListNode[] ps) {
        if (ps.length == 0) return null;
        return split(ps, 0, ps.length - 1);
    }

    private ListNode split(ListNode[] ps, int i, int j) {
        if (i == j) return ps[i];
        int m = (i + j) >>> 1;
        ListNode left = split(ps, i, m);
        ListNode right = split(ps, m + 1, j);
        return margeTwoLists(left, right);
    }

    public ListNode margeTwoLists(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(9, null);
        ListNode p = s;
        while (p1 != null && p2 != null) {
            if (p1.val >= p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 == null) p.next = p2;
        if (p2 == null) p.next = p1;

        return s.next;
    }

}
