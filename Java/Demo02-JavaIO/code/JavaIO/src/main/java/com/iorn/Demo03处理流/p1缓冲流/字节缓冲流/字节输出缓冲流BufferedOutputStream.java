package com.iorn.Demo03处理流.p1缓冲流.字节缓冲流;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class 字节输出缓冲流BufferedOutputStream {

    @Test
    public void TestOutput() {

        final File file = new File("src/main/java/com/iorn/Demo03处理流/p1缓冲流/字节缓冲流/output.txt");

        FileOutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {

            outputStream = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(outputStream);

            bufferedOutputStream.write("hello".getBytes());
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (outputStream != null) {

                try {

                    bufferedOutputStream.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
