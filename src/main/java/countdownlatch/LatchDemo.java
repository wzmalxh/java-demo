package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class LatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountdownLatch latch = new CountdownLatch(10);
        long beginTime = System.currentTimeMillis();
        for(int i=0; i< 10; i++){
            Thread t = new Thread(latch);
            t.start();
        }
        latch.getLatch().await();
        long endTime = System.currentTimeMillis();
        System.out.println("计算耗时："+ (endTime - beginTime)/1000 + "秒");
    }

    static class CountdownLatch implements Runnable{

        private CountDownLatch latch;

        public CountdownLatch(int latch){
            this.latch = new CountDownLatch(latch);
        }

        public CountDownLatch getLatch(){
            return this.latch;
        }

        @Override
        public void run() {
            synchronized (this){
                for(int i=0; i< 1000000; i++) {
                    if(i % 24 == 0){
                        System.out.println(i);
                    }
                }
                latch.countDown();
            }

        }
    }
}
