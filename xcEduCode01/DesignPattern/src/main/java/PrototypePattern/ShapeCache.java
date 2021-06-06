package PrototypePattern;

import java.util.Hashtable;

public class ShapeCache {
    private static Hashtable<String,Shape> shapeHashtable=new Hashtable<String, Shape>();

    public static Shape getShape(String shapeId){
        Shape cacheShape = shapeHashtable.get(shapeId);
        return (Shape) cacheShape.clone();
    }


    public static void loadCache(){
        Rectangle rectangle = new Rectangle();
        rectangle.setId("1");
        shapeHashtable.put(rectangle.getId(),rectangle);

        Square square = new Square();
        square.setId("2");
        shapeHashtable.put(square.getId(),square);
    }
}
