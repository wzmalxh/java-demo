package multi;

public class LockWithSynchronizdMultiPrint {
    private static final Object lock = new Object();
    private static int current = 0; // 0 for A, 1 for B, 2 for C

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while(current % 3 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print("A");
                    current += 1;
                    lock.notifyAll();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while(current % 3 != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print("B");
                    current += 1;
                    lock.notifyAll();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    while(current % 3 != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.print("C");
                    current += 1;
                    lock.notifyAll();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static void print(char c) {
        synchronized (lock) {
            while (current % 3 != (c - 'A')) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.print(c);

            // Move to the next character
            current = (current + 1) % 3;
            lock.notifyAll(); // Notify all threads in case they're all waiting

            // Optionally, add a small delay to see the effect more clearly
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
