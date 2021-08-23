package desige24.Singleton;

import java.lang.Class;
import java.lang.reflect.Constructor;
public class Break {
    public static void main(String[] args) throws Exception{
        Class clazz = Class.forName("desige.Singleton.imp3");
        Constructor c = clazz.getDeclaredConstructor(null);

        c.setAccessible(true);

        imp3 s1 = (imp3) c.newInstance();
        imp3 s2 = (imp3) c.newInstance();
        //通过反射，得到的两个不同对象
        System.out.println(s1);
        System.out.println(s2);
    }
}
