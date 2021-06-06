package SingletonPattern;


//双重检锁
public class Singleton01 {
    private volatile static Singleton01 singleton01;

    private Singleton01(){

    }
    private static Singleton01 getSingleton01(){
        if (singleton01==null){
            synchronized (Singleton01.class){
                singleton01=new Singleton01();
            }
        }
        return singleton01;
    }
}
