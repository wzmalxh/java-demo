import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiPrintDemo {

    static int count = 0;

    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    static class ThreadPrintA implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try{
                    while (count % 3 != 0) {
                        try {
                            conditionA.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("A");
                    count += 1;
                    conditionB.signal();
                }finally {
                    lock.unlock();
                }

            }
        }
    }

    static class ThreadPrintB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try{
                    while (count % 3 != 1) {
                        try {
                            conditionB.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    System.out.println("B");
                    count += 1;
                    conditionC.signal();
                }finally {
                    lock.unlock();
                }

            }
        }
    }


    static class ThreadPrintC implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try{
                    while (count % 3 != 2) {
                        try {
                            conditionC.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("C");
                    count += 1;
                    conditionA.signal();
                }finally {
                    lock.unlock();
                }

            }
        }
    }

    public static void main(String[] args) {
        ThreadPrintA a = new ThreadPrintA();
        ThreadPrintB b = new ThreadPrintB();
        ThreadPrintC c = new ThreadPrintC();
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
    }
}
