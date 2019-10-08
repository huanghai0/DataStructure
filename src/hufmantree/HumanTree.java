package hufmantree;

import java.util.*;


public class HumanTree {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] datas = str.getBytes();
        List<Node> list = getNotes(datas);
        System.out.println(list);
        Node root = creatHuffmanTree(list);
        PreList(root);
        Map<Byte,String> codehuf = getCodes(root,stringBuilder,"");
        System.out.println(codehuf);
    }

    public static List<Node> getNotes(byte[] da) {
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();

        for (Byte b : da) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);

            } else {
                map.put(b, count + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static Node creatHuffmanTree(List<Node> list) {
        Byte r = 0;

        while (list.size() > 1) {
            Collections.sort(list);
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            Node parent = new Node(r, (leftNode.weight + rightNode.weight));
            parent.left = leftNode;
            parent.right = rightNode;
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }

    private static void PreList(Node node) {
        if (node != null) {
            node.perList();
        } else {
            System.out.println("空结点");
        }
    }

    static Map<Byte, String> codes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte, String > getCodes(Node node, StringBuilder stringBuilder, String code) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        stringBuilder1.append(code);
        if (node == null) {
            return null;
        }
        if (node.data == 0) {
            getCodes(node.left, stringBuilder1, "0");
            getCodes(node.right, stringBuilder1, "1");
        }else {
            codes.put(node.data,stringBuilder1.toString());
        }
        return codes;
    }


}

class Node implements Comparable<Node> {
    public byte data;
    public int weight;
    public Node left;
    public Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    public void perList() {
        System.out.println(this);
        if (this.left != null) {
            this.left.perList();
        }
        if (this.right != null) {
            this.right.perList();
        }
    }


}