package com.iron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Arrays.ArrayList类继承自AbstractList，实现了List接口。
 * 它重写了add()、remove()等修改List结构的方法，并将它们直接抛出UnsupportedOperationException异常，从而禁止了对List结构的修改。
 * 具体来说，Arrays.asList()方法返回的是Arrays类中的一个私有静态内部类ArrayList，它继承自AbstractList类，实现了List接口。
 */
public class Demo02_ArraysAsList问题 {

    public static void main(String[] args) {

        Question();
        Slove();
    }



    private static void Question() {

        try {
            final List<Integer> list = Arrays.asList(1, 2, 3);
            list.add(1);    // 不允许 add remove 等修改操作
        } catch (UnsupportedOperationException e) {

            e.printStackTrace();
        }
    }

    private static void Slove() {

        final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

        list.add(4);
        System.out.println(list);
        list.remove(0);
        System.out.println(list);
    }
}
