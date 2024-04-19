package site.lrm7.adj.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode3 {
    public int lengthOfLongestSubstring(String S) {
        char[] s = S.toCharArray();
        int n = s.length, ans = 0, i = 0;
        boolean[] has = new boolean[128];
        for (int j = 0; j < n; j ++ ) {
            char c = s[j];
            while (has[c]) {
                has[s[i]] = false;
                i++;
            }
            has[c] = true;
            ans = Math.max(ans, j - i + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode3().lengthOfLongestSubstring("dvdf"));
    }
}
