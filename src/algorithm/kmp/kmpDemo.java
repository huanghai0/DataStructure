package algorithm.kmp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class kmpDemo {
    public static void main(String[] args) {
        //从文本文件中读数据转化为字符串。
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader bf = new BufferedReader(new FileReader("s.txt"));
            String s = null;
            while ((s = bf.readLine()) != null){
                buffer.append(s.trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        String str1 = buffer.toString();


        //String str1 = "SHGI ABCDABDKO ";
        String str2 = "BCDABD";
        //kmpNext(str2);
        int index = SearchKmp(str1, str2, kmpNext(str2));
        System.out.println(index);
    }

    public static int SearchKmp(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
//            else {  //暴力匹配
//                i = i - j;
//                j = 0;
//            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        System.out.println(Arrays.toString(next));
        return next;
    }

}
