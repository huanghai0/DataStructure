package head;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerAndConsumer {
    public static void main(String[] args) {
        BlockingQueue queue = new LinkedBlockingDeque(10);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);
        Consumer consumer3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer1);
        service.execute(consumer2);
        service.execute(consumer3);
        try {
            Thread.sleep(10 * 1000);
            producer1.stop();
            producer2.stop();
            producer3.stop();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    public void run() {
        PCData data = null;
        Random r = new Random();
        System.out.println("start Producer id = " + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                System.out.println(data + "is put into queue");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("fileed to put data" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}

class Consumer implements Runnable {
    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue) {

        this.queue = queue;
    }

    public void run() {
        System.out.println("start Conmuer id = " + Thread.currentThread().getId());
        Random r = new Random();
        try {
            while (true) {
                PCData data = queue.take();
                if (null != data) {
                    int re = data.GetData() * data.GetData();
                    //System.out.println(re);
                    System.out.println(MessageFormat.format("{0}*{1}={2}", data.GetData(), data.GetData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

final class PCData {
    private final int intData;

    public PCData(int d) {
        intData = d;
    }

    public PCData(String d) {
        intData = Integer.valueOf(d);
    }

    public int GetData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}