package com.iorn.Demo03处理流.p3对象流;

import org.junit.Test;

import java.io.*;

/**
 * 对象输入流(fanbianyi)
 */
public class 对象输入流ObjectInputStream {

    @Test
    public void TestObjectInputStream() {

        final File file = new File("src/main/java/com/iorn/Demo03处理流/p3对象流/output.txt");
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {

            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Employee employee = (Employee) objectInputStream.readObject();
            System.out.println(employee);
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (objectInputStream != null) {

                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
