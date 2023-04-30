package lv.demo.waitNotifyDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程交替打印1-100，即一个打印奇数，一个打印偶数。
 */
public class WaitNotifyDemo1 {

    public static void main(String[] args) throws InterruptedException {

        Object o = new Object();
        AtomicInteger count = new AtomicInteger();

        new Thread(() -> {
            synchronized (o) {
                while (count.get() < 100) {
                    System.out.println(Thread.currentThread().getName() + ": " + count.incrementAndGet());
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                while (count.get() < 100) {
                    System.out.println(Thread.currentThread().getName() + ": " + count.incrementAndGet());
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                o.notify();
            }
        }).start();

        Thread.sleep(10000);
    }
}
