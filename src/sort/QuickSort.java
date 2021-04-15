package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {3, 78, -188, 9, -1, 30, 1, 758, -33, -555, 33, -962, 561, 520};
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[80000000];
//        for (int i = 0; i < 80000000; i++) {
//            arr[i] = (int) (Math.random() * 800000);
//        }
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = simpleDateFormat.format(date1);
//        System.out.println(date1Str);

        //     quickSort2(arr, 0, arr.length - 1);
        //  System.out.println(Arrays.toString(arr));
//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println(date2Str);
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int p = arr[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            while (arr[l] < p) {
                l += 1;
            }
            while (arr[r] > p) {
                r -= 1;
            }
            if (l >= r) {
                break;
            } else {
                temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
            if (arr[l] == p) {
                r -= 1;
            }
            if (arr[r] == p) {
                l += 1;
            }

        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    public static void quickSort2(int[] arr, int left, int right) {
        int l = left;
        int r = right;

        if (l > r) return;
        int point = arr[l];
        while (l < r) {
            while (r > l && arr[r] >= point) {
                r--;
            }
            arr[l] = arr[r];
            while (l < r && arr[l] <= point) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = point;
        quickSort2(arr, left, l - 1);
        quickSort2(arr, l + 1, right);
    }

}
