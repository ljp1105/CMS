package PrototypePattern;

public class PrototypePatternTest {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape shape = ShapeCache.getShape("1");
        System.out.println(shape.getType());

        Shape shape1 = ShapeCache.getShape("2");
        System.out.println(shape1.getType());

    }
}
