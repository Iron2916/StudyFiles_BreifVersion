package com.iorn.Demo03处理流.p2转换流;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 将字符转为字符存入磁盘：是Wirter子类，是从字符流到字节流的桥梁。使用指定的字符集将字符编码为字节。
 */
public class 转换输出流OutputStreamWriter {

    @Test
    public void TestOutputStreamWriter() {

        final File file = new File("src/main/java/com/iorn/Demo03处理流/p2转换流/output.txt");
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;

        try {

            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");

            outputStreamWriter.write("你好啊！");
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (outputStreamWriter != null) {

                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                   e.printStackTrace();
                }
            }
        }
    }
}
