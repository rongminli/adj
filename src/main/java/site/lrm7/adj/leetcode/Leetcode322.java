package site.lrm7.adj.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

public class Leetcode322 {
    static int min = -1;

    public int change(int amount, int[] coins) {
        rec(coins.length -1 , coins, amount, new AtomicInteger(-1));
        return min;
    }
    public void rec(int index, int[] coins,  int remainder, AtomicInteger count) {
        count.incrementAndGet();
        if (remainder < 0) {

        }else if (remainder  == 0) {
            min = min == -1 ? count.get() : Integer.min(min, count.get());
        }else{
            for (int i =  index; i >= 0; i--) {
                rec(i, coins, remainder - coins[i], count);
            }
        }
        count.decrementAndGet();
    }

    public static void main(String[] args) {
        Leetcode322 solution = new Leetcode322();
        int result = solution.change(21, new int[]{1,10,15});
        System.out.println(result);
    }
}
