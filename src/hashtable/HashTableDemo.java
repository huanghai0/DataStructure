package hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        LinkedTable linkedTable = new LinkedTable(5);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("添加: add");
            System.out.println("删除： del");
            System.out.println("显示： list");
            System.out.println("退出： exit");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入 Id :");
                    int id = scanner.nextInt();
                    System.out.println("请输入name:");
                    String name = scanner.next();
                    linkedTable.add(id, name);
                    break;
                case "del":
                    System.out.println("请输入 Id :");
                    id = scanner.nextInt();
                    linkedTable.del(id);
                    break;
                case "list":
                    linkedTable.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}

class LinkedTable {
    public int linkSize;
    public Linked[] linked;

    public LinkedTable(int size) {
        this.linkSize = size;
        this.linked = new Linked[size];
        for (int i = 0; i < size; i++) {
            linked[i] = new Linked();
        }
    }

    public void add(int id, String name) {
        int l = hashCode(id);
        linked[l].addNode(id, name);
    }

    public void del(int id) {
        int code = hashCode(id);
        linked[code].delNodeById(id);
    }


    public int hashCode(int id) {
        return id % linkSize;
    }

    public void list() {
        for (int i = 0; i < linkSize; i++) {
            linked[i].show(i);
        }
    }

}


class Linked {
    public int size;
    public Node head;

    public Linked() {
        this.head = null;
    }

    class Node {
        public int id;
        public String name;
        public Node next;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }


    public void addNode(int id, String name) {
        if (head == null) {
            head = new Node(id, name);
            size++;
            return;
        }
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new Node(id, name);
        size++;
    }

    public void delNodeById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        if ( head.next ==null) {
            if(head.id == id) {
                head = head.next;
                size--;
                return;
            }
            System.out.println("链表中没有改id");
        }

        Node cur = head.next;
        Node pre = head;
        while (cur.id != id) {
            if (cur.next == null) {
                System.out.println("链表中没有改id");
                return;
            }
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = cur.next;
        size--;
    }

    public void show(int i) {
        if (head == null) {
            System.out.println("第" + (i + 1) + "条链表为空!");
            return;
        }
        Node cur = head;

        while (cur.next != null) {
            System.out.printf("第%d条链表 [id = %d , name = %s]" + "\t", (i + 1), cur.id, cur.name);
            cur = cur.next;
        }
        System.out.printf("第%d条链表 [id = %d , name = %s]" + "\t", (i + 1), cur.id, cur.name);
        System.out.println();
    }

}