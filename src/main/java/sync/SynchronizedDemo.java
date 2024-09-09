package sync;

import java.util.concurrent.*;

public class SynchronizedDemo {
    static ExecutorService executorService = Executors.newFixedThreadPool(2);
    private volatile static int cnt = 0;
    public static void main(String[] args) {

        Runnable task1 = () -> {
            for (int i = 0; i < 1000; i++) {
                cnt++;
            }
            System.out.println("Task 1: cnt = " + cnt);
        };

        Runnable task2 = () -> {
                for (int i = 0; i < 1000; i++) {
                    cnt++;
                }
            System.out.println("Task 2: cnt = " + cnt);
        };

        // 提交任务
        Future<?> future1 = executorService.submit(task1);
        Future<?> future2 = executorService.submit(task2);

        // 等待两个任务完成
        try{
            future1.get();
            future2.get();
        }catch (ExecutionException e) {

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // 理论上，如果每个任务都成功执行了1000次递增，cnt应该是2000
        // 但由于没有同步，实际结果可能小于2000
        System.out.println("Final cnt (without synchronization): " + cnt);
        System.out.println(cnt);
    }
}
