package lv.demo;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch readyLatch = new CountDownLatch(5);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(5);
        long start = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            for (int i = 0; i < 5; i++) {
                final int no = i + 1;
                executorService.submit(() -> {
                    try {
                        Thread.sleep(RandomUtils.nextInt(1000, 5000));
                        System.out.println(no + "号运动员做好准备..." + (System.currentTimeMillis() - start));
                        readyLatch.countDown();

                        startLatch.await();
                        System.out.println(no + "号运动员起跑..." + (System.currentTimeMillis() - start));
                        Thread.sleep(RandomUtils.nextInt(9000, 12000));
                        System.out.println(no + "号运动员到达终点" + (System.currentTimeMillis() - start));
                        endLatch.countDown();
                    } catch (InterruptedException ignored) {
                    }
                });
            }

            //
            readyLatch.await();
            System.out.println("裁判员鸣枪！" + (System.currentTimeMillis() - start));
            startLatch.countDown();
            endLatch.await();
            System.out.println("比赛结束！" + (System.currentTimeMillis() - start));
        }
    }
}
