package site.lrm7.adj.algorithm;

import java.util.HashSet;
import java.util.Set;

public class E06Fibonacci {

    private static int f(int n) {
        if (n == 1) return 1;
        if (n == 0) return 0;
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(f(10));
        Set<Integer> s = new HashSet<>();

    }
}
