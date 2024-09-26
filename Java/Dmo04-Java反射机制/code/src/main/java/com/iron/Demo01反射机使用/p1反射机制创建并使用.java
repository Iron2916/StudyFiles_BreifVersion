package com.iron.Demo01反射机使用;


import com.iron.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class p1反射机制创建并使用 {

    @Test
    public void getObjectByReflection() throws Exception{

        // 获取class实例
        final Class<Person> clazz = Person.class;           // 获取到类对象
        final Person person = clazz.newInstance();          // 获取到实例

        // 通过实例调用属性
        final Field ageFile = clazz.getField("age");    // 通过类对象拿到File
        ageFile.set(person, 14);                              // 通过File和实例设置age
        final int age = (int) ageFile.get(person);            // 通过File拿到age
        System.out.println("age = " + age);

        // 通过实例调用方法
        final Method show = clazz.getMethod("show");    // 通过类对象拿到方法(如果这里涉及到方法的重载，那么就在name后面传递参数)
        show.invoke(person);

    }
}
