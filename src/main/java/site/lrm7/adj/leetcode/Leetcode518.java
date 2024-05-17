package site.lrm7.adj.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode518 {
    private Map<String, Integer> cache = new HashMap<>();

    public int change(int amount, int[] coins) {
        return rec(coins.length -1, coins, amount);
    }
    public int rec(int index, int[] coins,  int remainder) {
        String key = index + ":" + remainder;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int result = 0;
        if (remainder  == 0) {
            result = 1;
        }else if(remainder > 0){
            int count = 0;
            for (int i =  index; i >= 0; i--) {
                count += rec(i, coins, remainder - coins[i]);
            }
            result = count;
        }
        cache.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        Leetcode518 solution = new Leetcode518();
        int result = solution.change(500, new int[]{3, 5, 7, 8, 9, 10, 11});
        System.out.println(result);
    }
}
