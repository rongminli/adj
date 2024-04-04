package site.lrm7.adj.algorithm;

public class BinarySearch {
    public static int search(int[] a, int t) {
        int i = 0;
        int j = a.length;
        while (i <j - 1) {
            int m = (i + j) >>> 1;
            if (t >= a[m]) {
                i = m;          // to left
            } else {
                j = m - 1;      // to right
            }
        }
        return j;
    }

    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 2, 3,3,3, 4, 6, 7, 8};
        System.out.println(search(a, 3));
    }
}
