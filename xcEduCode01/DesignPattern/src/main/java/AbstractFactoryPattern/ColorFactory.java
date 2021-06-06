package AbstractFactoryPattern;

public class  ColorFactory extends AbstratFactory {
    public Color getColor(String color) {
        if(color.equalsIgnoreCase("RED")) {
            return new Red();
        }else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }

    public Shape getShape(String shape) {
        return null;
    }
}
