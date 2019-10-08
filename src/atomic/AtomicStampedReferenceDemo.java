package atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {

    static AtomicStampedReference<Integer> menoy = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            final int stampedTime = menoy.getStamp();
            new Thread() {
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = menoy.getReference();
                            if (m < 20) {
                                if (menoy.compareAndSet(m, m + 20, stampedTime, stampedTime + 1)) {
                                    System.out.println("送20元");
                                    System.out.println("余额为1：" + menoy.getReference());
                                    break;
                                } else {
                                  //  System.out.println("大于20元，不送");
                                    break;
                                }
                            }
                        }
                    }
                }
            }.start();
        }

        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Integer stampedTime = menoy.getStamp();
                    Integer m = menoy.getReference();
                    while (true) {
                        if (m > 10) {
                            if (menoy.compareAndSet(m, m - 10, stampedTime, stampedTime + 1)) {
                                System.out.println("余额大于10元");
                                System.out.println("余额为：" + menoy.getReference());
                                break;
                            } else {
                                System.out.println("余额小于10，不能消费");
                                break;
                            }
                        }
                    }
                }
            }
        }.start();

    }

}
