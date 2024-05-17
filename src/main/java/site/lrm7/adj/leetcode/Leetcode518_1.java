package site.lrm7.adj.leetcode;

import java.util.Arrays;

public class Leetcode518_1 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin: coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
            System.out.println("coin   : " +coin );
            System.out.println("amounts: " + " 0, 1, 2, 3, 4, 5");
            System.out.println("dp     : " + Arrays.toString(dp));
            System.out.println();
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        Leetcode518_1 solution = new Leetcode518_1();
        int result = solution.change(5, new int[]{1,2,5});
        System.out.println("result : " + result);
    }
}
