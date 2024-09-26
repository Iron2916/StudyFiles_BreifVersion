package com.iorn.Demo03处理流.p1缓冲流.字符缓冲流;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class 字符输入缓冲流bufferedReader {

    @Test
    public void TestInput() {

        // 第一步：创建File类对象
        final File file = new File("src/main/java/com/iorn/Demo02节点流/p1字符节点流/input.txt");

        // 第二步：创建对应的流
        FileReader reader = null;
        BufferedReader bufferedReader = null;
        try {

            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader, 1024);

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);
            }
        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {

            // 第三步：关闭流
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
