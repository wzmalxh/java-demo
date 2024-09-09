package async;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Future {
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(3,5,1000,TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(500), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());



    public static void main(String[] args) throws InterruptedException {
        Consumer<Integer> consumer = t -> {
            process();
        };
        consumer.accept(100);

        CountDownLatch latch = new CountDownLatch(10);
        Consumer<String> printMessage = message -> System.out.println(message);
        printMessage.accept("Hello, world!");

        for (int i=0; i < 10; i++) {
            // 线程池执行异步方法
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+ "ends");
                    latch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }
        System.out.println("Now main thread stops");
        latch.await();
        System.out.println("Main thread resume !");


    }

    private static void process() {
        int sum = 200;
        for(int i=0; i< 19; i++) {
            sum += sum * i;
        }
        System.out.println("sum value is: "+ sum);
    }
}
