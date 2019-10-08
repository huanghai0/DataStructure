package arrayqueue;

import java.util.ArrayList;

public class ArrayQueue {
    public int size;
    public int fornt;
    public int raer;
    public int[] arr;

    public ArrayQueue(int size) {
        this.size = size;
        this.fornt = -1;
        this.raer = -1;
        this.arr = new int[size];
    }

    public boolean isEntry() {
        if (fornt == raer) {
            return true;
        } else {
            return false;
        }
    }

    public void push(int value) {
        ++raer;
        arr[raer] = value;
    }

    public int pop() {
        if (isEntry()) {
            new RuntimeException("为空");
        }
        ++fornt;
        return arr[fornt];
    }

    public void list() {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d \n", fornt+1, arr[++fornt]);
        }
    }

    public int peekHead(){

        return arr[fornt+1];
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue =new ArrayQueue(3);
        arrayQueue.push(1);
        arrayQueue.push(2);
        arrayQueue.push(3);
        arrayQueue.pop();
        System.out.println(arrayQueue.peekHead());

        arrayQueue.list();
    }
}
