package treetest;

public class TreeDemo {

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>();
        Node<String> node = new Node<>("A");
        Node<String> node1 = new Node<>("B");
        Node<String> node2 = new Node<>("C");
        Node<String> node3 = new Node<>("D");
        Node<String> node4 = new Node<>("E");
        Node<String> node5 = new Node<>("F");
        Node<String> node6 = new Node<>("G");

        tree.addNode(node);
        tree.addNode(node1);
        tree.addNode(node2);
        tree.addNode(node3);
        tree.addNode(node4);
        tree.addNode(node5);
        tree.addNode(node6);

        System.out.println(tree.Serch("E", node));

//        tree.listPre(node);
//        tree.listMid(node);
        tree.listLast(node);
        System.out.println();
        System.out.println("head :" + tree.getHead());


    }
}

class Node<E> {
    public E value;

    public Node left;
    public Node right;

    public Node(E e) {
        this.value = e;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

class Tree<E> {
    public Node<E> head;


    public Tree() {
        this.head = null;
    }

    public Tree(E e) {
        this.head = new Node<>(e);
    }

    public Node getHead() {
        return head;
    }

    public void addNode(Node<E> node) {
        if (head == null) {
            head = node;
            return;
        }
        Node<E> curNote = head;
        if (curNote.left != null) {
            while (curNote.right != null) {
                curNote = curNote.right;
            }
            curNote.right = node;
        } else {
            while (curNote.left != null) {
                curNote = curNote.left;
            }
            curNote.left = node;
        }
    }

    public boolean isEntry() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public Node Serch(E v, Node node) {
//            if(head == null){
//                return null;
//            }
        if (node.value == v) {
            return node;
        }
        Node res = null;
        if (node.left != null) {
           res = Serch(v, node.left);
        }
        if(res != null){
            return res;
        }
        if (node.right != null) {
           res = Serch(v, node.right);
        }
        return res;
    }


    public void listPre(Node head) {

        System.out.print(head);
        System.out.println();
        if (head.left != null) {
            listPre(head.left);
        }
        if (head.right != null) {
            listPre(head.right);
        }
    }

    public void listMid(Node head) {

        if (head != null) {
            if (head.left != null) {
                listMid(head.left);
            }
            System.out.print(head);
            if (head.right != null) {
                listMid(head.right);
            }
        } else {
            System.out.println("树为空！");
        }
    }

    public void listLast(Node head) {
        if (head != null) {
            if (head.left != null) {
                listLast(head.left);
            }

            if (head.right != null) {
                listLast(head.right);
            }
            System.out.print(head);
        } else {
            System.out.println("树为空");
        }
    }
}