package print;

import java.util.Arrays;

public class MaxitPrint {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20}
        };
        int[][] arr1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        int[] res = print(arr);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * @param arr
     */
    public static int[] print(int[][] arr) {
        int top = 0;
        int left = 0;
        int right = arr[0].length - 1;
        int botten = arr.length - 1;

        int[] res = new int[arr.length * arr[0].length];
        int index = 0;

        String stat = "right";
        while (left < right && left < right) {
            if ("right".equals(stat)) {
                for (int i = left; i <= right; i++) {
                    System.out.print(arr[top][i] + " ");
                    res[index++] = arr[top][i];
                }
                top++;
                stat = "botten";
                System.out.println();
            } else if ("botten".equals(stat)) {
                for (int i = top; i <= botten; i++) {
                    System.out.print(arr[i][right] + " ");
                    res[index++] = arr[i][right];
                }
                right--;
                stat = "left";
                System.out.println();
            } else if ("left".equals(stat)) {
                for (int i = right; i >= left; i--) {
                    System.out.print(arr[botten][i] + " ");
                    res[index++] = arr[botten][i];
                }
                botten--;
                stat = "top";
                System.out.println();
            } else if ("top".equals(stat)) {
                for (int i = botten; i >= top; i--) {
                    System.out.print(arr[i][left] + " ");
                    res[index++] = arr[i][left];
                }
                left++;
                stat = "right";
                System.out.println();
            }
        }
        return res;
    }
}
