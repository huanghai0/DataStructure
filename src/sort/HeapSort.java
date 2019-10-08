package sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {0, 6, 1, 5, -1, 90, -9987,768,8845,46356,-565, 852};

        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void creatHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length-1; k = k * 2 + 1) {
            if (arr[k] < arr[k + 1] && k + 1 < length) {
                k++;
            }
            if (temp < arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    public static void heapSort(int[] arr) {
        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            creatHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            creatHeap(arr, 0, j);
        }
    }
}
