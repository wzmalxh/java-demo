import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){

    };

    public static SimpleDateFormat getCurrentDateFormat(){
        return threadLocal.get();
    }

    public static void main(String[] args) {
        threadLocal.set(new SimpleDateFormat("YYYY-MM-DD"));
        String date = threadLocal.get().format(new Date());
        System.out.println(date);
    }
}
