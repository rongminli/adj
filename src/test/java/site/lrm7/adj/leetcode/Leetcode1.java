package site.lrm7.adj.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leetcode1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.containsKey(nums[i])) {
                return new int[] {m.get(i), i};
            }
            m.put(target - nums[i], i);
        }
        return null;
    }
}
