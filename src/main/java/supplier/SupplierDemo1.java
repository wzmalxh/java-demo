package supplier;

import java.util.Random;
import java.util.function.Supplier;

public class SupplierDemo1 {
    public static void main(String[] args) {
        Supplier<Integer> supplier = () -> {
            int i=0;
            i+=1;
            Random r = new Random(100);

            return r.nextInt() * i + i;
        };

        System.out.println(supplier.get().intValue());
    }
}
