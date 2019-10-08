package algorithm.hourse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;


public class TrevalChessBoard {
    private static int X;
    private static int Y;
    private static boolean[] visited;
    private static boolean funish;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        visited = new boolean[X * Y];
        int[][] chessboard = new int[X][Y];
        System.out.println("~~~");

        long start = System.currentTimeMillis();
        treval(chessboard, 0, 0, 1);
        long end = System.currentTimeMillis();

        System.out.println("耗时" + (end - start) + "ms");
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    public static void treval(int[][] chessboard, int row, int cloumn, int step) {

        ArrayList<Point> psn = next(new Point(cloumn, row));
        Sort(psn);
        chessboard[row][cloumn] = step;
        visited[row * Y + cloumn] = true;


        while (!psn.isEmpty()) {
            Point p = psn.remove(0);
            if (!visited[p.y * X + p.x]) {
                treval(chessboard, p.y, p.x, step + 1);
            }
        }
        if (!funish && step < X * Y) {
            chessboard[row][cloumn] = 0;
            visited[row * X + cloumn] = false;
        } else {
            funish = true;
        }

    }

    public static ArrayList<Point> next(Point curpoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x = curpoint.x - 1) >= 0 && (p1.y = curpoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curpoint.x - 1) >= 0 && (p1.y = curpoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curpoint.x - 2) >= 0 && (p1.y = curpoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curpoint.x - 2) >= 0 && (p1.y = curpoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curpoint.x + 1) < X && (p1.y = curpoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curpoint.x + 1) < X && (p1.y = curpoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curpoint.x + 2) < X && (p1.y = curpoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curpoint.x + 2) < X && (p1.y = curpoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    public static void Sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (next(o1).size() < next(o2).size()) {
                    return -1;
                } else if (next(o1).size() == next(o2).size()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}


