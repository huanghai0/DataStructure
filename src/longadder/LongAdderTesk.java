package longadder;

import lock.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;


public class LongAdderTesk implements Runnable {

    public static final int Thread_Count = 5;
    public static final int Task_Count = 3;
    public static final int Target_Count = 10000;

    private static final LongAdder la = new LongAdder();
    public static final CountDownLatch cdl = new CountDownLatch(Task_Count);
    public String name;
    public long startTime;

    public LongAdderTesk(long count) {
        this.startTime = count;
    }

    public void run() {
        long v = la.sum();
        while (v < Target_Count) {
            la.increment();
            v = la.sum();
        }
        long end = System.currentTimeMillis();
        System.out.println("spent: " + (end - startTime) + "ms , v= " + v);
        cdl.countDown();

    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(Thread_Count);
        long start = System.currentTimeMillis();
        LongAdderTesk task = new LongAdderTesk(start);
        for (int i = 0; i < 5; i++) {
            pool.submit(task);
            cdl.await();
        }
        pool.shutdown();
    }

}

