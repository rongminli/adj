package site.lrm7.adj.algorithm.sort;

import java.util.Arrays;

public class ShellSort {
    public static void sort(int[] a) {
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                int temp = a[i];
                int j;
                for (j = i; j >= gap && a[j - gap] > temp; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] { 1, 2, 3, 9, 7, 6 };
        sort(a);
        System.out.println(Arrays.toString(a));

    }
}
