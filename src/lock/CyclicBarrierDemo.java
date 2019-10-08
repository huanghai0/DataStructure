package lock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static class Soilder implements Runnable {
        private final CyclicBarrier cyclic;
        private String SoilderName;

        Soilder(CyclicBarrier cyclic, String SoilderName) {
            this.cyclic = cyclic;
            this.SoilderName = SoilderName;
        }

        public void run() {
            try {
                cyclic.await();
                doWork();
                cyclic.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt() % 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(SoilderName + "任务完成");
        }
    }

    public static class BarrierRun implements Runnable{
        int N;
        boolean flag;
        public BarrierRun(int N , boolean flag){
            this.N = N;
            this.flag =flag;
        }
        public void run(){
            if(flag){
                System.out.println("司令：【"+N+"任务完成】");
            }else {
                System.out.println("司令：【"+N+"集合完成】");
                flag =true;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final int N = 10;
        Thread[] allSoidler = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(N,flag));
        System.out.println("开始集合");
        for(int i =0 ; i< N; ++i){
            System.out.println("第"+i+"士兵报道");
            allSoidler[i] = new Thread(new Soilder(cyclic,"士兵"+i));
            allSoidler[i].start();
        }

    }
}
