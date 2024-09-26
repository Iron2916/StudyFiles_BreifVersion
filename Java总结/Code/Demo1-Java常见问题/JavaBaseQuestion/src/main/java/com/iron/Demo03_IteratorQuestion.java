package com.iron;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class Demo03_IteratorQuestion {

    public static void main(String[] args) {
        Question();
        Slove();
    }

    private static void Question() {

        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext())
        {
            Integer value = iterator.next();
            if(value == 12)
            {
                try {

                    list.remove(value); // Iterator 遍历中不能使用 Remove Add
                    list.add(16);
                } catch (ConcurrentModificationException e) {

                    e.printStackTrace();
                }

            }
        }
        list.forEach(v -> System.out.println(v));
    }

    private static void Slove() {

        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext())
        {
            Integer value = iterator.next();
            if(value == 12)
            {
                iterator.remove();
            }
        }
        list.forEach(v -> System.out.println(v));
    }
}
