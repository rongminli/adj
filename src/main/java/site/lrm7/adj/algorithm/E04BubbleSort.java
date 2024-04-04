package site.lrm7.adj.algorithm;

import java.util.Arrays;

public class E04BubbleSort {
    public static void sort(int[] a) {
        doSort(a, a.length -1);
    }

    // j 代表未排序区域的右边界
    public static void doSort(int[] a, int j) {
        if (j == 0) return;
        int x = 0;
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i+1]) {
                int t = a[i];
                a[i] = a[i+1];
                a[i+1] = t;
                x = i;
            }
        }
        doSort(a, x);
    }

    public static void main(String[] args) {
        int[] a = {4,6,8,9,3,5,6,4,8,7,6,1,5,3};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
