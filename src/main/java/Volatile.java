public class Volatile {

    public static void main(String[] args) {
        ThreadVolatile t = new ThreadVolatile();
        new Thread(t).start();
        while (true) {
            if (t.getIsTrue()) {
                System.out.println("thread value is true");
                break;
            }
        }
    }

    static class ThreadVolatile implements Runnable{
        private volatile boolean isTrue = false;
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                isTrue = true;
                System.out.println("isTrue flag is : "+ isTrue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        public boolean getIsTrue(){
            return this.isTrue;
        }

    }
}
