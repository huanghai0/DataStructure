package leetcode;

import java.util.Arrays;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class ListedSort {
    public static void main(String[] args) {
        int[] arr = {0, 6, 1, 5, 90, 768, 8845, 46356, 565, 852};
        //shellSort(arr);
        // insertSort(arr);

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid , temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int t = 0;
        int i = left;
        int j = mid + 1;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <=right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        t = 0;
        int templeft = left;
        while (templeft <= right) {
            arr[templeft] = temp[t];
            t++;
            templeft++;
        }
    }

//    public static void insertSort(int[] arr) {
//        int i, j, key = 0;
//        for (i = 1; i < arr.length; i++) {
//            key = arr[i];
//            j = i - 1;
//            while (j >= 0 && arr[j] > key) {
//                arr[j + 1] = arr[j];
//                j--;
//            }
//            arr[j + 1] = key;
//        }
//    }
//
//    public static void shellSort(int[] arr) {
//        int sub = arr.length / 2;
//        int i, j, key;
//
//        while (sub >= 1) {
//            for (i = 1; i < arr.length; i += sub) {
//                key = arr[i];
//                j = i - sub;
//                while (j >= 0 && arr[j] > key) {
//                    arr[j + sub] = arr[j];
//                    j -= sub;
//                }
//                arr[j + sub] = key;
//            }
//            sub = sub / 2;
//        }
//    }


//    class Solution {
//        public ListNode sortList(ListNode head) {
//            if (head==null) return null;
//            return fen( head );
//        }
//        public ListNode bing(ListNode start,ListNode left)
//        {
//            ListNode s = start;
//            ListNode l = left;
//            ListNode node = null;
//            while(start!=null && left!=null)
//            {
//                if(start.val<=left.val) {
//                    if(node!=null) node.next = start;
//                    node = start;
//                    start = start.next;
//                }else{
//                    if(node!=null) node.next = left;
//                    node = left;
//                    left = left.next;
//                }
//            }
//            if(start==null) node.next=left;
//            if(left==null) node.next=start;
//            if (s.val<=l.val)  return s;
//            return l;
//        }
//        public ListNode fen(ListNode node)
//        {
//            if(node.next==null) return node;
//            ListNode root = node.next;
//            node.next = null;
//            ListNode q = bing( node,fen( root ) );
//            return q;
//        }
//    }
}
