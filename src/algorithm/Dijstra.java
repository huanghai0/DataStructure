package algorithm;

import java.util.Arrays;

public class Dijstra {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);

        graph.djs(6);
        // graph.show();
        graph.Show();
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private Visitedvertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void Show() {

        for (int[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
        vv.show();

    }

    public void djs(int index) {
        vv = new Visitedvertex(vertex.length, index);
        updata(index);
        for (int j = 1; j < vertex.length; j++) {
            index = vv.updataArr();
            updata(index);
        }

    }

    public void updata(int index) {
        int len = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            len = vv.getDis(index) + matrix[index][j];
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updataPre(j, index);
                vv.updataDis(j, len);
            }
        }
    }
}

class Visitedvertex {
    int[] readly_arr;
    int[] pre_visited;
    int[] dis;

    public Visitedvertex(int length, int index) {
        this.readly_arr = new int[length];
        this.readly_arr[index] = 1;
        this.pre_visited = new int[length];
        //pre_visited[index] = 1;
        this.dis = new int[length];
        Arrays.fill(dis, 65535);
        dis[index] = 0;
    }

    public boolean in(int index) {
        return readly_arr[index] == 1;
    }

    public void updataDis(int index, int len) {
        dis[index] = len;
    }

    public void updataPre(int pre, int index) {
        pre_visited[pre] = index;
    }

    public int getDis(int index) {
        return dis[index];
    }

    public int updataArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < readly_arr.length; i++) {
            if (readly_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        readly_arr[index] = 1;
        return index;
    }

    public void show() {

        System.out.println(Arrays.toString(readly_arr));

        System.out.println(Arrays.toString(dis));

        System.out.println(Arrays.toString(pre_visited));

    }
}