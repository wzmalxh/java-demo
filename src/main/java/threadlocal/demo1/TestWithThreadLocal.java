package threadlocal.demo1;

import threadlocal.WithoutThreadLocal;

public class TestWithThreadLocal {

    private ThreadLocal<String> threadLocalContent = new ThreadLocal<>();

    private String content;

    public String getContent() {
        return threadLocalContent.get();
    }

    public void setContent(String content) {
        threadLocalContent.set(content);
    }

    public static void main(String[] args) {
        TestWithThreadLocal local = new TestWithThreadLocal();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String str = Thread.currentThread().getName();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    local.setContent(str + "的数据");
                    System.out.println(Thread.currentThread().getName() + " threadlocal is :" + local.getContent());
                }
            });
            t.setName("线程" + i);
            t.start();
        }
    }
}
