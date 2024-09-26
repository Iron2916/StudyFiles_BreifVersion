package com.iorn.Demo03处理流.p3对象流;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 对象输出流(序列化）
 */
public class 对象输出流ObjectOutputStream {

    @Test
    public void TestObjectOutputStream() {

        final File file = new File("src/main/java/com/iorn/Demo03处理流/p3对象流/output.txt");
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {

            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            final Employee employee = new Employee("张四", "北京市", 22);
            objectOutputStream.writeObject(employee);
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (objectOutputStream != null) {

                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
