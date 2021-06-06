package AnnotationTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Persion persion = new Persion();
        Class<Persion> persionClass = Persion.class;
        PersionAnnotation annotations = persionClass.getAnnotation(PersionAnnotation.class);
        System.out.println(Arrays.toString(annotations.value()));

//        Method somebody = persionClass.getMethod("somebody", String.class, int.class);
//        somebody.invoke(persion,new Object[]{"lily",18});
//        iteratorAnnotations(somebody);

    }

    public static void iteratorAnnotations(Method method){
        if (method.isAnnotationPresent(PersionAnnotation.class)){
            PersionAnnotation annotation = method.getAnnotation(PersionAnnotation.class);
            String[] value = annotation.value();
            for (String str : value) {
                System.out.println("==="+str);
            }



            //获取方法上的所有注解并打印出来
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation1 : annotations) {
                PersionAnnotation annotation2=(PersionAnnotation)annotation1;
                System.out.println(annotation2.value());
            }
        }
    }
}
