package concurrent;

import java.util.Arrays;

public class ShellSortDemo {
    public static void main(String[] args) {
        int[] arr = {46, 84, 456, 1, -8, 456, 9, -56, 995};
        //InsertSort(arr);
        // ShellSort(arr);
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void InsertSort(int[] arr) {
        int i, j;
        for (i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > tmp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = tmp;
        }
    }

    public static void ShellSort(int[] arr) {
        int grp = arr.length;

        while (true) {
            grp /= 2;
            for (int i = 0; i < grp; i++) {
                for (int j = i + grp; j < arr.length; j++) {
                    int tmp = arr[j];
                    int k = j - grp;
                    while (k >= 0 && arr[k] > tmp) {
                        arr[k + grp] = arr[k];
                        k -= grp;
                    }
                    arr[k + grp] = tmp;
                }
            }
            if (grp == 1) {
                break;
            }
        }
    }

    public static void shellSort(int[] arr) {
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            for (int i = h; i < arr.length; i++) {

                if (arr[i] < arr[i - h]) {
                    int tmp = arr[i];
                    int j = i - h;
                    while (j >= 0 && arr[j] > tmp) {
                        arr[j + h] = arr[j];
                        j -= h;
                    }
                    arr[j + h] = tmp;
                }
            }
            h = (h - 1) / 3;
        }
    }

}

