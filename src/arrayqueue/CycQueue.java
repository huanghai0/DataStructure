package arrayqueue;

public class CycQueue<E> {
    public int maxSize;
    public int fornt;
    public int rear;
    public E[] arr;

    public CycQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = (E[]) new Object[maxSize];
        this.fornt = 0;
        this.rear = 0;
    }

    public boolean isFull() {
        if ((rear + 1) % maxSize == fornt) {
            return true;

        } else {
            return false;
        }
    }

    public boolean isEntry() {
        if (fornt == rear) {
            return true;
        } else {
            return false;
        }
    }

    public void push(E value) {
        if (isFull()) {
            System.out.println("已满");
            return;
        }
        arr[rear] = value;
        rear = (rear + 1) % maxSize;

    }

    public E pop() {
        if (isEntry()) {
            new RuntimeException("为空");
        }
        E temp = arr[fornt];
        fornt = (fornt + 1) % maxSize;
        return temp;
    }

    public E peekHead() {
        if (isEntry()) {
            new RuntimeException("为空");
        }
        return arr[fornt];
    }

    public void list() {
        if (isEntry()) {
            System.out.println("为空");
            return;
        }
        for (int i = fornt; i < fornt + size(); i++) {
            //  System.out.println(arr[i%size()]);
            System.out.println("arr["+(i % maxSize)+"] = "+arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - fornt) % maxSize;
    }

    public static void main(String[] args) {
        CycQueue cycQueue = new CycQueue(4);
        cycQueue.push("str");
        cycQueue.push("wqe");
        cycQueue.push("qde");

        cycQueue.pop();
        cycQueue.push(4);
        cycQueue.push(5);
        cycQueue.list();

    }

}
