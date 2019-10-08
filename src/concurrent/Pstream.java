package concurrent;

import java.awt.datatransfer.MimeTypeParseException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Pstream {
    public static void main(String[] args) {
        new Thread(new Puls()).start();
        new Thread(new Mul()).start();
        new Thread(new Div()).start();

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                Msg msg = new Msg();
                msg.i = i;
                msg.j = j;
                msg.str = "((" + i + "+" + j + ")*" + i + ")/2";
                Puls.bq.add(msg);
            }
        }

    }
}

class Msg {
    public double i;
    public double j;
    public String str;

}

class Puls implements Runnable {
    public static BlockingQueue<Msg> bq = new LinkedBlockingDeque<>();

    public void run() {
        while (true) {
            try {
                Msg msg = new Msg();
                msg = bq.take();
                msg.j = msg.i + msg.j;
                Mul.bq.add(msg);
            } catch (InterruptedException e) {
            }
        }

    }
}

class Mul implements Runnable {
    public static BlockingQueue<Msg> bq = new LinkedBlockingDeque<>();

    public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.i = msg.i * msg.j;
                Div.bq.add(msg);
            } catch (InterruptedException e) {

            }
        }
    }
}

class Div implements Runnable {
    public static BlockingQueue<Msg> bq = new LinkedBlockingDeque<>();

    public void run() {
        while (true) {
            try {
                Msg msg = bq.take();
                msg.i /= 2;
                System.out.println(msg.str + "=" + msg.i);
            } catch (InterruptedException e) {
            }
        }
    }
}