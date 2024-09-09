package fanxing;

public class FanxingClass2<T extends Number> {

    private T var;
    public void setVar(T var){
        this.var = var ;
    }
    public T getVar(){
        return this.var ;
    }
    public String toString(){    // 直接打印
        return this.var.toString() ;
    }

    public static void main(String[] args) {

    }

}
