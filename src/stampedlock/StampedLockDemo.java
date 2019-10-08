package stampedlock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    public void move(double curx, double cury) {
        long stamped = sl.writeLock();
        try {
            x += curx;
            y += cury;
        } finally {
            sl.unlockWrite(stamped);
        }
    }

    public double Origin() {
        long stamped = sl.tryOptimisticRead();
        double currentx = x;
        double currenty = y;

        if (!sl.validate(stamped)) {
            long readstamped = sl.readLock();
            try {
                currentx = x;
                currentx = y;
            } finally {
                sl.unlockRead(readstamped);
            }
        }
        return Math.sqrt(currentx * currentx + currenty * currenty);
    }

    public static void main(String[] args) {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();
        stampedLockDemo.move(0, 2);
        double res =stampedLockDemo.Origin();
        System.out.println(res);
    }

}
