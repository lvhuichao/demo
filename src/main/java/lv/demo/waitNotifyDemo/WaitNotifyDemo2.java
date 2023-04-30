package lv.demo.waitNotifyDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多个线程交替打印数字
 */
public class WaitNotifyDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        AtomicInteger count = new AtomicInteger();
        int threadNumber = 5;
        for (int i = 0; i < threadNumber; i++) {
            new Thread(new Print(i, o, count, threadNumber)).start();
        }
        Thread.sleep(2000);
    }

    private record Print(int mod, Object mutex, AtomicInteger count, int threadNumber) implements Runnable {

        @Override
        public void run() {
            synchronized (mutex) {
                while (count.get() <= 100) {
                    if (count.get() % threadNumber == mod) {
                        System.out.println(Thread.currentThread().getName() + ": " + count.getAndIncrement());
                        mutex.notifyAll();
                        try {
                            mutex.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            mutex.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                // 最后唤醒一下，避免最后打印的那个线程一直wait
                mutex.notify();
            }
        }
    }
}
