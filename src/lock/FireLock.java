package lock;

import java.util.Currency;
import java.util.concurrent.locks.ReentrantLock;

public class FireLock implements Runnable {
    ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run(){
        while (true){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FireLock fireLock = new FireLock();
        Thread t1 = new Thread(fireLock);
        Thread t2 = new Thread(fireLock);
        t1.start();
        t2.start();
        try{

            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }



    }
}
