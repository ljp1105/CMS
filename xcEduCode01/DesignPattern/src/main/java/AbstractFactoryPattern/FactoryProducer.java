package AbstractFactoryPattern;

public class FactoryProducer {
    public static AbstratFactory getFactory(String choice){
        if (choice.equals("shape")){
            return new ShapeFactory();
        }
        if (choice.equals("color")){
            return new ColorFactory();
        }
        return null;
    }
}
