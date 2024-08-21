package lock;

import sun.misc.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLockDemo {


    private static Lock lock = new Lock();

    public static void main(String[] args) {
        TickSell t1 = new TickSell();
        new Thread(t1, "售票机1").start();
        TickSell t2 = new TickSell();
        new Thread(t2, "售票机2").start();
        TickSell t3 = new TickSell();
        new Thread(t3, "售票机3").start();
    }

    static class TickSell implements Runnable {
        private  int ticket = 100;

        @Override
        public void run() {

            while (true) {
                try {
                    lock.lock();
                    if (ticket > 0) {
                        Thread.sleep(200);
                        System.out.println("操作线程名师：" + Thread.currentThread().getName() + "余票是：" + ticket);
                        ticket -= 1;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }

        }
    }
}
