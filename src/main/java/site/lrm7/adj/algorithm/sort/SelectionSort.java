package site.lrm7.adj.algorithm.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void sort(int[] a) {
        int j = a.length - 1;
        while (j > 0) {
            int max = j;
            for (int i = 0; i < j; i++) {
                if(a[i] > a[max]) {
                    max = i;
                }
            }
            int t = a[max];
            a[max] = a[j];
            a[j] = t;
            j--;
        }
    }
    
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,9,7,6};
        sort(a);
        System.out.println(Arrays.toString(a));

    }
}
