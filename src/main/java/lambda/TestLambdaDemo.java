package lambda;

import java.util.function.Function;

public class TestLambdaDemo {
    public static void main(String[] args) {
        //定义接口实现
        FuncTest p1 = () -> System.out.println("Hello Perform void function");
        //执行接口方法
        p1.perform();
        FunctionSupplier p2 = () -> {
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "new Supplier";
        };

        String p2Value = p2.callMethod();
        System.out.println(p2Value);
    }
}
