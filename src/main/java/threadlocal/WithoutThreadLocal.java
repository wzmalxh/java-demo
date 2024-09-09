package threadlocal;

public class WithoutThreadLocal implements Runnable {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void run() {
        String str = Thread.currentThread().getName();
        WithoutThreadLocal w = new WithoutThreadLocal();

    }

    public static void main(String[] args) {
        WithoutThreadLocal local = new WithoutThreadLocal();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String str = Thread.currentThread().getName();
                    /*try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }*/
                    local.setContent(str + "的数据");
                    System.out.println(Thread.currentThread().getName() + " threadlocal is :" + local.getContent());
                }
            });
            t.setName("线程" + i);
            t.start();
        }
    }
}
