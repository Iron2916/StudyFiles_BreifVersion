package com.iron.Demo01反射机使用;

import com.iron.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class p2反射机制调用私有方法和属性构造器{

    @Test
    public void TestGetPrivate() throws Exception{

        // 获取class实例
        final Class<Person> clazz = Person.class;

        /**
         *     private Person(String name, int age){
         *         this.name = name;
         *         this.age = age;
         *     }
         */

        // 获取私有构造器并创建对象
        final Constructor<Person> cons = clazz.getDeclaredConstructor(String.class, int.class);     // 这里必须调用 Declared 修饰
        cons.setAccessible(true);   // 设置可获取
        final Person person = cons.newInstance("张三", 18);

        /**
         * private String name;
         */
        // 获取私有属性
        final Field nameFile = clazz.getDeclaredField("name");
        nameFile.setAccessible(true);
        final String name = nameFile.get(person).toString();
        System.out.println("name = " + name);


        /**
         * private String showNation(String nation){
         *         return "我的国籍是：" + nation;
         *     }
         */
        // 调用私有方法
        final Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        final String message = showNation.invoke(person, "中国").toString();
        System.out.println(message);
    }
}
