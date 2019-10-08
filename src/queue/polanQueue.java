package queue;

import java.util.Stack;

public class polanQueue {
    public static void main(String[] args) {
        String[] str = {"3", "-4", "+"};
        Solution solution = new Solution();
        int res = solution.evalRPN(str);
        System.out.println(res);
    }
}

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String s : tokens) {
            if (s.matches("-?\\d+")) {
                stack.push(s);
            } else {
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int res = 0;
                if (s.equals("+")) {
                    res = num1 + num2;
                } else if (s.equals("-")) {
                    res = num1 - num2;
                } else if (s.equals("*")) {
                    res = num1 * num2;
                } else if (s.equals("/")) {
                    res = num1 / num2;
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}