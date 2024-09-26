package com.iorn.Demo03处理流.p1缓冲流.字节缓冲流;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class 字节输入缓冲流BufferedInputStream {

    @Test
    public void TestInput() {

        // 第一步
        final File file = new File("src/main/java/com/iorn/Demo02节点流/p2字节节点流/input.txt");

        FileInputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {

            // 第二步
            inputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(inputStream);

            int len;
            byte[] buffer = new byte[100];
            while ((len = bufferedInputStream.read(buffer)) != -1) {

                System.out.print(new String(buffer, 0, len));
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (inputStream != null) {

                try {
                    // 第三步
                    bufferedInputStream.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
