package sync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedDemo2 {
    private static int cnt = 0;
    static class ThreadTestA implements Runnable{
        @Override
        public void run() {
            synchronized (this) {
                for(int i=0; i< 100; i++) {
                    cnt+=1;
                }
            }
        }
    }

    static class ThreadTestB implements Runnable{
        @Override
        public void run() {
            synchronized (this) {
                for(int i=0; i< 100; i++) {
                    cnt+=1;
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Thread a = new Thread(new ThreadTestA());
        Thread b = new Thread(new ThreadTestB());
        a.start();
        b.start();
        System.out.println(cnt);
    }
}
