package site.lrm7.adj.algorithm;

public class E05InsertionSort {
    public static void sort(int[] a) {
        insertion(a, 1, a.length - 1);
    }
    private static void insertion(int[] a, int low, int height) {
        if (low == height) return;

        int t = a[low];
        int i = low - 1;
        while (i >= 0 && a[i] > t) {
            a[i + 1] = a[i];
            i--;
        }
        if (i + 1 != low) a[i + 1] = t;

        insertion(a, low + 1, height);
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        int i = 1;
        while (i < flowerbed.length - 1) {
            if (flowerbed[i] == 0){
                if (i ==0 || flowerbed[i-1] == 0) {
                    if(i+1 > flowerbed.length - 1 || flowerbed[i+1] == 0) {
                        i += 2;
                        n--;
                    }
                }
            }
            i++;

            if (flowerbed[i] == 1) {
                i += 2;
            }else if(i > 0 && flowerbed[i -1] ==1){
                i += (i+2 > flowerbed.length) ? 1 : (1+ flowerbed[i+1]);
            }else if(i+1 > flowerbed.length - 1 && flowerbed[i+1] ==1 ){
                i += 2;
            }else {
                n--;
                i +=2;
            }
            if (n == 0) return true;
        }

        return false;
    }
}
