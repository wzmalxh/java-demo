package async.consumer;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class User {
    public static void main(String[] args) {
        BiConsumer<Integer, Integer> biConsumer = (x, y) -> {
            System.out.println(x * y + x * y);
        };
        int a=3,b=4;
        addSquareSum(a, b, biConsumer);


        HashMap<String,Integer> map = new HashMap<>();
        map.put("wang",3);
        map.put("du",2);
        map.put("chen",1);

        BiConsumer<String, Integer> biConsumerNew = (key, value) -> {
            System.out.println("key-value is:"+ key + ":"+ value);
        };
        map.forEach(biConsumerNew);
    }

    private static void addSquareSum(int a, int b, BiConsumer<Integer, Integer> consumer) {
         consumer.accept(a,b);
    }


}
