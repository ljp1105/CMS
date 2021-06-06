package AbstractFactoryPattern;

public class AbstractFactoryPatternTest {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = (ShapeFactory) FactoryProducer.getFactory("shape");
        Shape shape = shapeFactory.getShape("square");
        shape.draw();


        ColorFactory colorFactory = (ColorFactory) FactoryProducer.getFactory("color");
        Color color= colorFactory.getColor("red");
        color.fill();
    }
}
