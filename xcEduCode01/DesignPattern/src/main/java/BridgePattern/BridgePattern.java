package BridgePattern;

public class BridgePattern {
    public static void main(String[] args) {
        Circle circle = new Circle(new RedCircle(), 100, 100, 10);

        Circle circle2 = new Circle(new GreenCircle(), 1000, 100, 10);
        circle.draw();
        circle2.draw();

    }

}
