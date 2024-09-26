package com.iorn.Demo02节点流.p1字符节点流;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 字符输入流：只能输入纯文本，按照字符为单位进行读取。
 */
public class 字符输入流Fileread {

    /**
     * 测试输入流
     */
    @Test
    public void TestInput() {

        // 第一步：创建File类对象
        final File file = new File("src/main/java/com/iorn/Demo02节点流/p1字符节点流/input.txt");

        // 第二步：创建对应的流
        FileReader reader = null;
        try {

            reader = new FileReader(file);

            int data;
            while ((data = reader.read()) != -1) {  // 返回该字符的Unicode编码值(int类型值)。如果已经到达流末尾了，则返回-1

                System.out.print((char) data);
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {

            // 第三步：关闭流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     * 根据Buffer一次性读取多个，减少磁盘的IO
     */
    @Test
    public void TestInputByBuffer() {

        // 第一步：创建File类对象
        final File file = new File("src/main/java/com/iorn/Demo02节点流/p1字符节点流/input.txt");

        // 第二步：创建对应的流
        final char[] buffer = new char[5];
        FileReader reader = null;

        try {

            reader = new FileReader(file);

            int len;
            while ((len = reader.read(buffer)) != -1) {     // 返回实际读取的字符个数。如果已经到达流末尾，没有数据可读，则返回-1

                for (int i = 0; i < len; i++) {
                    System.out.print(buffer[i]);
                }
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {

            // 第三步：关闭流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
