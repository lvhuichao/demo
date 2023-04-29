package lv.demo;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierDemo {

    /**
     * 20个人，4辆车，每辆车坐5人。每到5个人就发一辆车
     */
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                () -> System.out.println("第" + atomicInteger.incrementAndGet() + "辆车准备发车..."));

        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 20; i++) {
                int fi = i;
                executorService.submit(() -> {
                    try {
                        Thread.sleep(RandomUtils.nextInt(0, 2000));
                        System.out.println("乘客" + (fi + 1) + "到达");
                        cyclicBarrier.await();
                        System.out.println("乘客" + (fi + 1) + "上车");
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        Thread.sleep(10000);
    }
}
