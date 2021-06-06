package AnnotationTest;



@PersionAnnotation(value = {"33","44"})
public class Persion {

    @PersionAnnotation(value={"girl","boy"})
    public void somebody(String name, int age){
        System.out.println("\nsomebody: "+name+", "+age);
    }
}
