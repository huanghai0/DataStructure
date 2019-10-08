package stack;

import java.util.Scanner;

public class ArrayStackDome {
    public static void main(String[] args) {

        ArrayStack arrayStack = new ArrayStack(5);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:");
            System.out.println("exit:");
            System.out.println("push:");
            System.out.println("pop:");
            System.out.println("请输入:");
            key = scanner.next();
            switch (key){
                case "show":
                    arrayStack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入：");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    try {
                        int res = arrayStack.pop();
                        System.out.println(res);
                    }catch (Exception e){
                        System.out.println("空栈");
                    }
                    break;
                 default:
                     break;
            }
        }
        System.out.println("程序退出了");
    }

}

class ArrayStack {
    private int maxsize;
    private int stack[];
    private int top = -1;

    public ArrayStack(int maxsize) {
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }

    public boolean isFull() {
        return top == maxsize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("空栈");
        }
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]= %d\n", i, stack[i]);
        }
    }

}