package multi;

import java.util.concurrent.CountDownLatch;

public class ThreadWorkerDemo implements Runnable{
    private int threadCnt; // 活跃线程数量

    private final CountDownLatch latch;

    private volatile int initalNum = 0;
    public ThreadWorkerDemo(int threads, CountDownLatch latch) {
        this.threadCnt = threads;
        this.latch = latch;
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int sum = 0;
        for(int i= 0; i< 100; i++) {
            sum += i;
        }
        //倒计时
        System.out.println("Thread name is: "+ threadName+ "result is :"+ sum);
        latch.countDown();
    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println("准备开启子线程");
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1= new Thread(new ThreadWorkerDemo(5,latch));
        Thread t2= new Thread(new ThreadWorkerDemo(4,latch));
        t1.start();
        t2.start();
        latch.countDown();
        System.out.println("线程执行完毕");
    }
}
