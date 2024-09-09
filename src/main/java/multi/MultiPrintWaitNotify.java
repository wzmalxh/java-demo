package multi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiPrintWaitNotify {

    private static Lock lock = new ReentrantLock();
    private static Object objectA = new Object();
    private static Object objectB = new Object();
    private static Object objectC = new Object();

    private static int num = 0;

    public static void main(String[] args) {
        new Thread(new ThreadOne(objectA, objectB)).start();
        new Thread(new ThreadTwo(objectB, objectC)).start();
        new Thread(new ThreadThree(objectC, objectA)).start();
    }

    static class ThreadOne implements Runnable {
        private Object cur;
        private Object next;

        public ThreadOne(Object current, Object next) {
            this.cur = current;
            this.next = next;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (num % 3 != 0) {
                        cur.wait();
                    }
                    System.out.println("1");
                    num += 1;
                    next.notify();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadTwo implements Runnable {
        private Object cur;
        private Object next;

        public ThreadTwo(Object current, Object next) {
            this.cur = current;
            this.next = next;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (num % 3 != 1) {
                        cur.wait();
                    }
                    System.out.println("2");
                    num += 1;
                    next.notify();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    static class ThreadThree implements Runnable {
        private Object cur;
        private Object next;

        public ThreadThree(Object current, Object next) {
            this.cur = current;
            this.next = next;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (num % 3 != 2) {
                        cur.wait();
                    }
                    System.out.println("3");
                    num += 1;
                    next.notify();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
