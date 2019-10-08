package singellinkedlist;

public class BeconeDemo {
    public static void main(String[] args) {

        SingelLinkedList<Integer> list = new SingelLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.show();




    }

}
/**
 * public static Node Reverse2(Node head) {
 * if (head == null) return head;
 * Node pre = head;// 上一结点
 * Node cur = head.getNext();// 当前结点
 * Node tmp;// 临时结点，用于保存当前结点的指针域（即下一结点）
 * while (cur != null) {// 当前结点为null，说明位于尾结点
 * tmp = cur.getNext();
 * cur.setNext(pre);// 反转指针域的指向
 * pre = cur; // 指针往下移动
 * cur = tmp;
 * }
 * // 最后将原链表的头节点的指针域置为null，还回新链表的头结点，即原链表的尾结点
 * head.setNext(null);
 * return pre;
 * }
 */