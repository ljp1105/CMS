package BridgePattern;

public class RedCircle implements DrawAPI {
    public void drawCircle(int redius, int x, int y) {
        System.out.println("redCircle"+redius+","+x+","+y);
    }
}
