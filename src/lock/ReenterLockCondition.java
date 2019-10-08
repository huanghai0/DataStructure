package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {
    static ReentrantLock lock = new ReentrantLock();
    static Condition cl = lock.newCondition();

    public void run() {
        try {
            lock.lock();
            cl.await();
            System.out.println("Thread is going");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.newCondition();
        }
    }

    public static void main(String[] args) {
        ReenterLockCondition r = new ReenterLockCondition();
        Thread t = new Thread(r);
        t.start();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        lock.lock();
        cl.signal();
        lock.unlock();

    }
}
