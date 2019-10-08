package algorithm;

import java.util.Arrays;

public class Prim {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        MGraph graph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        minTree.showGraph(graph);
        minTree.prim(graph, 0);


    }


}


class MinTree {
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            graph.data = data;
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void prim(MGraph graph, int v) {
        int vitiser[] = new int[graph.verxs];
        vitiser[v] = 1;
        int h1 = 0;
        int h2 = 0;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {

            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (vitiser[i] == 1 && vitiser[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("<" + graph.data[h1] + "," + graph.data[h2] + ">" + graph.weight[h1][h2]);
            vitiser[h2] = 1;
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs;
    char[] data;
    int[][] weight;

    public MGraph(int verx) {
        this.verxs = verx;
        data = new char[verx];
        weight = new int[verx][verx];
    }

}