package algorithm.flody;

import java.util.Arrays;

public class Flody {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        Graph graph = new Graph(matrix, vertex);
        //  graph.show();
        graph.flody();
        graph.show();


    }
}

class Graph {
    char[] vertex;
    int[][] pre;
    int[][] dis;

    public Graph(int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], i);
        }
        this.dis = new int[vertex.length][vertex.length];
        dis = matrix;
    }

    public void show() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            for (int j = 0; j < vertex.length; j++) {
                System.out.print("<" + vertex[i] + "到" + vertex[j] + "的距离" + dis[i][j] + ">" + " ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void flody() {
        int len = 0;
        for (int k = 0; k < vertex.length; k++) {
            for (int i = 0; i < vertex.length; i++) {
                for (int j = 0; j < vertex.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }

    }
}


