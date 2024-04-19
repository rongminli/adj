package site.lrm7.adj.leetcode;

import java.util.*;

public class Leetcode49 {
    public List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    static class ArrayKey {
        int[] key = new int[26];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Objects.deepEquals(key, arrayKey.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }

        public ArrayKey(String str) {
            for (char c: str.toCharArray()) {
                key[c-97]++;
            }
        }
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<ArrayKey, List<String>> map = new HashMap<>();
        for (String str : strs) {
            ArrayKey key = new ArrayKey(str);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    public boolean containsDuplicate1(int[] nums) {
        HashMap<Integer, Object> map = new HashMap<>(nums.length * 2);
        Object value = new Object();
        for (int num : nums) {
            Object put = map.put(num, value);
            if (put != null) {
                return true;
            }
        }
        return false;
    }

    public int singleNumber(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            num ^= nums[i];
        }
        return num;
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int key: nums) {
            if(!set.add(key)) {
                return true;
            }
        }
        return false;
    }

    public int firstUniqChar(String s) {

        return  0;
    }
}
