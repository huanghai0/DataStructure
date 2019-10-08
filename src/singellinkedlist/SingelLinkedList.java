package singellinkedlist;

public class SingelLinkedList<T> {
    class Node<T> {
        private T vaule;
        Node next;

        public Node(T vaule, Node node) {
            this.vaule = vaule;
            this.next = node;
        }

        public T getVaule() {
            return vaule;
        }
    }

    private Node head;
    private Node tail;
    int Size;

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public SingelLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public SingelLinkedList(T value) {
        head = new Node(value, null);
        tail = head;
        Size++;
    }

    public void add(T value) {
        if (head == null) {
            head = new Node(value, null);
            tail = head;
            Size++;
        } else {
            Node temp = new Node(value, null);
            tail.next = temp;
            tail = temp;
            Size++;
        }
    }

    public void delByIndex(int index) {
        if (index < 0 || index >= Size) {
            System.out.println("下标越界");
        }
        if (index == 0) {
            head = head.next;
            Size--;
        }
        for (int i = 1; i < Size; i++) {
            if (index == i) {
                Node pre = getNodeByIndex(i - 1);
                Node cur = getNodeByIndex(i);
                if (cur.next == null) {
                    tail = pre;
                    Size--;
                    break;
                }
                pre.next = cur.next;
                Size--;
            }
        }
    }


    public void delByValue(T value) {
        if (head == null) {
            System.out.println("链表为空");
        } else {
            Node node = head.next;
            Node temp = head;
            while (node.vaule != value) {
                temp = temp.next;
                node = node.next;
                if (node.next == null && node.vaule != value) {
                    System.out.println("链表中没有要删除的这个元素");
                    return;
                }
            }
            if (node.next == null) {
                tail = temp;
                Size--;
            } else {
                temp.next = node.next;
                Size--;
            }
        }
    }

    public Object get(int index) {
        return getNodeByIndex(index).vaule;
    }

    private Node getNodeByIndex(int index) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        if (index < 0 || index >= Size) {
            System.out.println("下标越界");
            return null;
        }
        Node current = head;
        for (int i = 0; i < Size - 1; i++) {
            if (i == index) {
                break;
            }
            current = current.next;
        }
        return current;
    }

    public void show() {
        for (int i = 0; i < Size; i++) {
            System.out.print(getNodeByIndex(i).vaule + "\t");
        }
        System.out.println();
    }

    public void ReverseShow(Node head) {
        if (head == null) {
            System.out.println("链表为空");
        }
        while (head.next != null) {
            System.out.print(head.getVaule() + "\t");
            head = head.next;
        }
        if (head.next == null) {
            System.out.print(head.getVaule());
        }
    }

    public void addHead(T value) {
        if (head == null) {
            head = new Node(value, null);
            tail = head;
            Size++;
        } else {
            head = new Node(value, head);
            Size++;
        }
    }

    public void change(int index, T value) {
        Node node = getNodeByIndex(index);
        node.vaule = value;
    }

    public int getSize() {
        return Size;
    }

    public Node Reverse(Node head) {
        if (head == null) return head;
        Node p = head;
        Node q = head.next;
        Node r;
        while (q != null) {
            r = q.next;
            q.next = p;

            p = q;
            q = r;
        }
        head.next = null;
        return p;
    }

    public Node Reverse2(Node head) {
        if (head == null) return head;
        Node pre = head;// 上一结点
        Node cur = head.next;// 当前结点
        Node tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
        while (cur != null) {// 当前结点为null，说明位于尾结点
            tmp = cur.next;
            cur.next = pre;// 反转指针域的指向
            pre = cur; // 指针往下移动
            cur = tmp;
        }
        // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
        head.next = null;
        return pre;
    }

    public static void main(String[] args) {
        SingelLinkedList<String> list = new SingelLinkedList<>();
        list.add("T");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");

        list.addHead("A");
        list.change(5, "Z");
        list.delByIndex(2);
        list.delByIndex(0);
        list.delByIndex(5);
        list.show();
        System.out.println("链表尾元素：" + list.tail.vaule);
        int length = list.getSize();
        System.out.println("链表长度:" + length);
        list.Reverse(list.head);
        list.ReverseShow(list.tail);

    }

}
