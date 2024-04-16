package site.lrm7.adj.datastructure;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static  ListNode of(int...elements) {
        ListNode head = null;
        for (int i = elements.length - 1; i >= 0;  i--) {
            head = new ListNode(elements[i],head);
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        ListNode p = this;
        while (p != null) {
            str.append(p.val).append(" -> ");
            p = p.next;
        }
        str.append("null");
        return str.toString();
    }
}
