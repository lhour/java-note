package javasecode.Myant.Annotation;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws Exception{

        Class userclass = Class.forName("javasecode.Myant.Annotation.User");
        
        if(userclass.isAnnotationPresent(Id.class)){
            Field[] fields = userclass.getDeclaredFields();
            boolean tar = false;
            for(Field f : fields){
                if("id".equals(f.getName()) && "int".equals(f.getType().getSimpleName())){
                    tar = true;
                    break;
                }
            }

            if(!tar){
                throw new NoIdException("被@Id标注的类中必须有一个 int 类型的 id 属性");
            }
        }

        System.out.println("hello world");
        
    }
}
