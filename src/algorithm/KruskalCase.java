package algorithm;

import java.util.Arrays;

public class KruskalCase {
    private char[] vertexs;
    private int edgeNums;
    private int[][] matrixs;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrixs = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrixs);
        kruskalCase.print();
        EData[] eDate = kruskalCase.getEdges();
        kruskalCase.sortEdges(eDate);
        // System.out.println("XX" + Arrays.toString(kruskalCase.getEdges()));
        kruskalCase.kruskal();


    }

    public KruskalCase(char[] vertexs, int[][] matrixs) {
        int len = vertexs.length;
        this.vertexs = new char[len];
        for (int i = 0; i < len; i++) {
            this.vertexs[i] = vertexs[i];
        }

        this.matrixs = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                this.matrixs[i][j] = matrixs[i][j];
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (this.matrixs[i][j] != INF) {
                    edgeNums++;
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d\t", matrixs[i][j]);
            }
            System.out.println();
        }
    }

    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    //    System.out.println(Arrays.toString(edges));
    }

    private int getIndex(char c) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges() {
        int index = 0;
        EData[] eData = new EData[edgeNums];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrixs[i][j] != INF) {
                    eData[index++] = new EData(vertexs[i], vertexs[j], matrixs[i][j]);
                }
            }
        }
        return eData;
    }

    /**
     * 获取下标为i的顶点的终点
     *
     * @param end
     * @param i
     * @return
     */
    private int getEnd(int[] end, int i) {
        while (end[i] != 0) {
            i = end[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNums];
        EData[] res = new EData[edgeNums];
        EData[] edges = getEdges();
        sortEdges(edges);
        for (int i = 0; i < edges.length; i++) {
            int m = getIndex(edges[i].start);
            int n = getIndex(edges[i].end);
            int h1 = getEnd(ends, m);
            int h2 = getEnd(ends, n);
            if (h1 != h2) {
                ends[h1] =h2;
                res[index++] = edges[i];
            }


        }
        for(int i= 0; i< index;i++) {
            System.out.println(res[i]);
        }
    }

}


class EData {
    public char start;
    public char end;
    public int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                " , " + end +
                "> weight=" + weight +
                '}';
    }
}
