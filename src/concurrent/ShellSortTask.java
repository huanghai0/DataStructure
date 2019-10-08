package concurrent;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShellSortTask implements Runnable {
    int i;
    int h;
    CountDownLatch l;
    int[] arr;

    public ShellSortTask(int i, int h, CountDownLatch latch, int[] arr) {
        this.i = i;
        this.h = h;
        this.l = latch;
        this.arr = arr;
    }

    @Override
    public void run() {

        if (arr[i] < arr[i - h]) {
            int tmp = arr[i];
            int j = i - h;
            while (j >= 0 && arr[j] > tmp) {
                arr[j + h] = arr[j];
                j -= h;
            }
            arr[j + h] = tmp;
        }
        l.countDown();
    }

    public static void pShellSort(int[] arr) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        int h = 1;
        CountDownLatch latch = null;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        while (h > 0) {
            if (h >= 4) {
                latch = new CountDownLatch(arr.length - h);
            }
            for (int i = h; i < arr.length; i++) {
                if (h >= 4) {
                    pool.execute(new ShellSortTask(i, h, latch, arr));

                } else {

                    if (arr[i] < arr[i - h]) {
                        int tmp = arr[i];
                        int j = i - h;
                        while (j >= 0 && arr[j] > tmp) {
                            arr[j + h] = arr[j];
                            j -= h;
                        }
                        arr[j + h] = tmp;
                    }
                }
            }
            latch.await();
            h = (h - 1) / 3;
        }
       pool.shutdown();
    }

    public static void main(String[] args) {
       // int[] arr = {4, 78, 6, 884, -29, -441, 455, 7899, 535, 789, 852, 9651, 74, 86, 100, -555, -588, -77989, -47, 89563};
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }

        try {
            Date date1 = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date1Str = simpleDateFormat.format(date1);
            System.out.println(date1Str);

            pShellSort(arr);

            Date date2 = new Date();
            String date2Str = simpleDateFormat.format(date2);
            System.out.println(date2Str);
           // System.out.println(Arrays.toString(arr));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
