package site.lrm7.adj.datastructure;

import org.junit.jupiter.api.Test;

class DoublyLinkedListSentinelTest {

    @Test
    void insert() {
        DoublyLinkedListSentinel linkedList = new DoublyLinkedListSentinel();
        linkedList.insert(0, 0);
        linkedList.insert(0, 1);
        linkedList.insert(0, 2);
        linkedList.insert(0, 3);

    }

    int jech(int n) {
        if (n == 0) return 1;
        return n * jech(n - 1);
    }

    void printstrrev(String str, int index) {
        if (index == str.length()) return;
        printstrrev(str, index + 1);
        System.out.println(str.charAt(index));
    }
    @Test
    void test1() {
        System.out.println(jech(3));
        printstrrev("abcde", 0);
    }
}