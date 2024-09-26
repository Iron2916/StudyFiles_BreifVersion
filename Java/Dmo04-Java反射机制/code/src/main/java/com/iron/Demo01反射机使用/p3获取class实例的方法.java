package com.iron.Demo01反射机使用;

import com.iron.Person;
import org.junit.jupiter.api.Test;

public class p3获取class实例的方法 {

    @Test
    public void getClassMethods() throws Exception{

        // 第一种方式：调用静态属性.class
        final Class<Person> clazz1 = Person.class;

        // 第二种方式：调用运行时类对象的getClass
        final Person person = new Person();
        final Class<? extends Person> clazz2 = person.getClass();

        // 第三种方式：调用Class的静态方法
        String className = "com.iron.Person";               // 类的全类名
        final Class<?> clazz3 = Class.forName(className);

        // 第四种方式：使用类的加载器的方式
        final Class<?> clazz4 = ClassLoader.getSystemClassLoader().loadClass(className);

        // 因为Java虚拟机在加载和管理类的过程中，确保了对于同一个类的Class对象只有一个实例存在，不论通过何种方式获取。
        System.out.println(clazz1 == clazz2);   // true
        System.out.println(clazz2 == clazz3);   // true
        System.out.println(clazz3 == clazz4);   // true
    }
}
