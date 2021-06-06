package SingletonPattern;

public class Singleton02 {

    private static  class Singleton02Holder{
        private static final Singleton02 INSTANCE = new Singleton02();
    }
    private Singleton02(){

    }
    public static final Singleton02 getInstance(){
        return Singleton02Holder.INSTANCE;
    }
}
