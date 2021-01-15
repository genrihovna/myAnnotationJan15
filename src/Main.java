import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        Test test = new Test();
        new Main().annotationProcessing(test);
        System.out.println(test.foo());
    }

    public void annotationProcessing(Object test){
        //get Test class
        Class testClass = test.getClass();
        Object privateFieldObject = null;

        //do testClass fields have my custom annotation
        for (Field privateField : testClass.getDeclaredFields()){
            privateField.setAccessible(true);
            Annotation annotation = privateField.getAnnotation(AutoInitialize.class);
            if(annotation instanceof AutoInitialize){
                //initialize field
                Class classPrivateFieldObject = privateField.getType();
                try {
                    Constructor constructor = classPrivateFieldObject.getConstructor();
                    privateFieldObject = constructor.newInstance();
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
