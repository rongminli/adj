package site.lrm7.adj.leetcode;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Leetcode105Test {

    @Test
    public void evalRPN() {
        String[] tokens = new String[] {"2","1","+","3","*"};
        int result = Leetcode105.evalRPN(tokens);
        assertEquals(9, result);
        assertEquals(22,
                Leetcode105.evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }
}