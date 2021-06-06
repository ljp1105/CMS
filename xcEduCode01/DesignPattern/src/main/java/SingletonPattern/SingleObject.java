package SingletonPattern;


//饿汉式 线程不安全
public class SingleObject {
    private static SingleObject singleObject=new SingleObject();

    private SingleObject(){

    }

    public static SingleObject getInstance(){
        return singleObject;
    }
    public void showMessage(){
        System.out.println("hello word");
    }
}
