package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Palindrome {
    public static void main(String[] args) {
        String s = "a  a";
        Solution solution = new Solution();
        boolean flag = solution.isPalindrome(s);
        System.out.println(flag);
    }
}

class Solution {
    public boolean isPalindrome(String s) {
        boolean flag = false;
        String st = s.replaceAll("\\W", "").toLowerCase();
        if (st.equals("")) {
            flag = true;
        }

        System.out.println(st);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < st.length(); i++) {
            list.add(st.substring(i, i + 1));
        }
        System.out.println(list);
        String[] str = list.toArray(new String[list.size()]);

//        for(int i= 0 ;i<=str.length;i++){
//            for(int j = str.length-1;j>=0;j--){
//
//                if(str[i].equals(str[j])){
//                    return flag = true;
//                }else {
//                    return false;
//                }
//            }
//        }
        int i = 0, j = str.length-1;
        while (str[i].equals(str[j])){
            i++;
            j--;
            if(i>str.length-1 && j<0){
                flag =true;
                break;
            }
        }
            System.out.println(flag);
        return flag;
    }
}