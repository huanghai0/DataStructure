package algorithm;

import java.util.Arrays;

/**
 * 时间复杂度 O(n^2)
 */
public class DijkstraDemo {

    public static void main(String[] args) {
//        float[][] a = new float[3][3];
//        for (float[] tmp : a) {
//            Arrays.fill(tmp, Float.MAX_VALUE);
//        }

//        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        float[] dist = new float[7];
        int[] prev = new int[dist.length];
        final float N = Float.MAX_VALUE;
        float[][] matrix = new float[dist.length][dist.length];
        matrix[0] = new float[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new float[]{5, N, N, 9, N, N, 3};
        matrix[2] = new float[]{7, N, N, N, 8, N, N};
        matrix[3] = new float[]{N, 9, N, N, N, 4, N};
        matrix[4] = new float[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new float[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new float[]{2, 3, N, N, 4, 6, N};

        dijkstra(0, matrix, dist, prev);
    }

    /**
     * @param v    开始的节点
     * @param a    节点之间 距离 的二维矩阵
     * @param dist 记录 源节点 到 各个节点之间的最短距离
     * @param prev 记录 源节点 的 目的节点 的前一个节点
     */
    public static void dijkstra(int v, float[][] a, float[] dist, int[] prev) {

        int n = dist.length - 1;
        if (v < 0 || v > n) {
            return;
        }

        /**
         * 初始化 dist[] prev[] 数组
         */
        boolean[] s = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = a[v][i];
            s[i] = false;
            if (dist[i] == Float.MAX_VALUE) {
                prev[i] = 0;
            } else {
                prev[i] = v;
            }
        }

        dist[v] = 0;
        s[v] = true;

        System.out.println("dist[] init: " + Arrays.toString(dist));
        System.out.println("prev[] init: " + Arrays.toString(prev));


        for (int i = 0; i <= n; i++) {
            float temp = Float.MAX_VALUE;
            int u = v;

            /**
             * 1.探索可以联通的节点
             * 2探索可以联通节点 之间的最短距路径节点u
             * 3.s[u]=true 将节点 u 加入到 s 集合
             */
            for (int j = 0; j <= n; j++) {
                if (!s[j] && dist[j] < temp) {
                    u = j;
                    temp = dist[j];
                }
            }
            s[u] = true;

            /**
             *计算 节点u 到 节点j 之间的最短路径并更新 dist[] prev[]
             */
            for (int j = 0; j <= n; j++) {
                if (!s[j] && a[u][j] < Float.MAX_VALUE) {
                    float newDist = dist[u] + a[u][j];
                    if (newDist < dist[j]) {
                        dist[j] = newDist;
                        prev[j] = u;
                    }
                }
            }

        }

        System.out.println("s[] res: " + Arrays.toString(s));
        System.out.println("dist[] res: " + Arrays.toString(dist));
        System.out.println("prev[] res: " + Arrays.toString(prev));

    }
}
