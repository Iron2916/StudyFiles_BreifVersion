package com.iorn.Demo03处理流.p2转换流;


import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 转换输入流：是Reader子类，将 字节数据 转换为指定的 编码字符数据 读入。
 */
public class 转换输入流InputStreamReader {

    @Test
    public void TestFileInputReader() {

        final File file = new File("src/main/java/com/iorn/Demo03处理流/p2转换流/input.txt");
        FileInputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {

            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream, "gbk");

            final char[] buffer = new char[5];
            int len;

            while ((len = inputStreamReader.read(buffer)) != -1) {

                final String s = new String(buffer, 0, len);
                System.out.print(s);
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (inputStreamReader != null) {

                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
