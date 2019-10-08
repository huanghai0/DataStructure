package sorttree;

public class SortTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {7, 3, 10, 1, 5, 9, 12, 2};
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        SortTree sortTree = new SortTree();
        for (int i = 0; i < arr.length; i++) {
            sortTree.add(new Node(arr[i]));
        }
        Node root = sortTree.getHead();
        System.out.println("根结点为：" + sortTree.getHead());

        sortTree.Midlist();
        System.out.println();
        System.out.println("树的高度：" + root.hight());
        System.out.println("左子树的高度：" + root.leftHight());
        System.out.println("右子树的高度：" + root.rightHight());
        System.out.println("找到结点：" + sortTree.Search(7));
        System.out.println("找到目标结点的父结点：" + sortTree.SearchParent(9));


    }

}

class SortTree {
    public Node head;

    //构造SortTree树
    public SortTree() {
        this.head = null;
    }

    //返回头结点
    public Node getHead() {
        return head;
    }

    //添加结点
    public void add(Node node) {
        if (head == null) {
            head = node;
        } else {
            head.addNode(node);
        }
    }

    //删除以当前结点为根结点树的结点，并返回该节点的value
    public int delRightTreeMin(Node node) {
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp.value;
    }

    //传入一个参数，删除该节点
    public void delNode(int v) {
        if (head == null) {
            return;
        } else {
            Node target = Search(v);
            if (target == null) {
                return;
            }
            if (head.left == null && head.right == null) {
                head = null;
                return;
            }
            Node parent = SearchParent(v);

            if (target.left == null && target.right == null) {  //要删除结点为叶子结点
                if (parent.left != null && parent.left.value == v) {
                    parent.left = null;
                    return;
                } else if (parent.right != null && parent.right.value == v) {
                    parent.right = null;
                    return;
                } else {
                    return;
                }
            } else if (target.left != null && target.right != null) {//要删除的结点有两个子结点
                target.value = delRightTreeMin(target.right);
                return;

            } else {//要删除的结点只有一个子结点
                if (target.left != null && parent.left.value == target.value) {
                    parent.left = target.left;
                } else if (target.right != null && parent.right.value == target.value) {
                    parent.right = target.right;
                } else if (target.right != null && parent.left.value == target.value) {
                    parent.left = target.right;
                } else if (target.left != null && parent.right.value == target.value) {
                    parent.right = target.left;
                }
            }


        }
    }

    //查找结点并返回
    public Node Search(int v) {
        if (head == null) {
            System.out.println("数为空");
            return null;
        }
        return head.SerchNote(v);
    }

    //查找结点的父结点并返回父结点
    public Node SearchParent(int v) {
        if (head == null || head.value == v) {
            return null;
        }
        return head.SerchNodeParent(v);
    }

    //前序遍历
    public void Prelist() {
        if (head == null) {
            System.out.println("树为空");
            return;
        }
        head.preList();
    }

    //中序遍历
    public void Midlist() {
        if (head == null) {
            System.out.println("树为空");
            return;
        }
        head.midList();
    }
}

class Node {
    int value;
    Node left;
    Node right;

    //构造Note结点
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //查找结点
    public Node SerchNote(int v) {
        if (this.value == v) {
            return this;
        } else if (v < this.value) { //如果传入参数v小于当前结点值，则从当前结点的左子树中递归查找
            if (this.left == null) {
                return null;
            } else {
                return this.left.SerchNote(v);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.SerchNote(v);
            }
        }
    }

    //查找目标结点的父结点
    public Node SerchNodeParent(int v) {
        if ((this.left != null && this.left.value == v) || (this.right != null && this.right.value == v)) {
            return this;
        } else {
            if (this.left != null && v < this.value) {
                return left.SerchNodeParent(v);
            } else if (this.right != null && v >= this.value) {
                return right.SerchNodeParent(v);
            } else {
                return null;
            }
        }
    }

    //返回当前结点左子树高度
    public int leftHight() {
        if (left == null) {
            return 0;
        }
        return left.hight();
    }

    //返回以当前结点为根结点的右子树高度
    public int rightHight() {
        if (right == null) {
            return 0;
        }
        return right.hight();
    }

    //返回以当前结点为根节点的树的高度
    public int hight() {
        return Math.max(left == null ? 0 : left.hight(), right == null ? 0 : right.hight()) + 1;
    }

    //添加结点
    public void addNode(Node note) {
        if (this == null) {
            return;
        }

        if (note.value < this.value) {
            if (this.left == null) {
                this.left = note;
                return;
            } else {
                this.left.addNode(note);
            }
        } else {
            if (this.right == null) {
                this.right = note;
            } else {
                this.right.addNode(note);
            }
        }
        //当右子树高度，左子树大于1时，进行左旋转
        if (rightHight() - leftHight() > 1) {
            //如果以当前结点的右子结点为根结点的左子树高度大于右子树高度，则先对当前结点的右子结点进行右旋转，在对当前结点进行左旋转
            if (right != null && right.leftHight() > right.rightHight()) {
                right.rightRoteta();
                leftRoteta();
            } else {
                leftRoteta();
            }
            return;
        }

        if (leftHight() - rightHight() > 1) {
            if (left != null && left.rightHight() > left.leftHight()) {
                left.leftRoteta();
                rightRoteta();
            } else {
                rightRoteta();
            }
        }

    }

    //前序遍历
    public void preList() {
        System.out.print(this);
        if (this.left != null) {
            this.left.preList();
        }
        if (this.right != null) {
            this.right.preList();
        }
    }

    //中序遍历
    public void midList() {
        if (this.left != null) {
            this.left.midList();
        }
        System.out.print(this);
        if (this.right != null) {
            this.right.midList();
        }
    }

    //左旋转
    public void leftRoteta() {
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        right = right.right;
        left = newNode;
    }

    //右旋转
    public void rightRoteta() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
}