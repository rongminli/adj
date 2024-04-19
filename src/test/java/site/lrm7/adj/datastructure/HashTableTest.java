package site.lrm7.adj.datastructure;

import junit.framework.TestCase;

public class HashTableTest extends TestCase {

    public void testGet() {
    }

    public void testPut() {
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三");
        table.put(17, "li", "李四");
        table.put(2, "wang", "王五");

        assertEquals(3, table.size);
        assertEquals("张三",table.table[1].value);
        assertEquals("李四",table.table[1].next.value);
        assertEquals("王五",table.table[2].value);

        table.put(1, "zhang", "张4");
        table.put(17, "li", "李5");
        assertEquals("张4",table.table[1].value);
        assertEquals("李5",table.table[1].next.value);
    }

    public void testRemove() {
        HashTable table = new HashTable();
        table.put(1, "zhang", "张三");
        table.put(17, "li", "李四");
        table.put(2, "wang", "王五");

        assertEquals(3, table.size);
        assertEquals("张三",table.table[1].value);
        assertEquals("李四",table.table[1].next.value);
        assertEquals("王五",table.table[2].value);

        table.remove(1, "jiang");
        assertEquals(3, table.size);

        table.remove(1, "zhang");
        assertEquals(2, table.size);
        assertEquals("李四",table.table[1].value);

        table.put(1, "zhang", "张三");
        assertEquals("张三",table.table[1].next.value);
    }
}