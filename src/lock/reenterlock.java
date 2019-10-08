package lock;

import java.util.concurrent.locks.ReentrantLock;

public class reenterlock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static int i =0;
    @Override
    public void run(){
        for(int j= 0; j<10000000; j++){
            try{
                lock.lock();
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        reenterlock t = new reenterlock();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){

        }
        System.out.println(i);
    }

}
