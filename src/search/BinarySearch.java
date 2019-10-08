package search;


public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int res = binarySearch(arr, 0, args.length - 1, 2);
        System.out.println(res);
    }

    public static int binarySearch(int[] arr, int left, int right, int val) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (val < midVal) {
            return binarySearch(arr, left, mid - 1, val);
        } else if (midVal > arr[mid]) {
            return binarySearch(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }
}
