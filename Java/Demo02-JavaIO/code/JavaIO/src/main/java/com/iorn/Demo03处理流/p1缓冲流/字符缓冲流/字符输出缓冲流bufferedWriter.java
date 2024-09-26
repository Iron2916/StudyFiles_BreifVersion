package com.iorn.Demo03处理流.p1缓冲流.字符缓冲流;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class 字符输出缓冲流bufferedWriter {

    @Test
    public void testNewLine()throws IOException {
        // 创建流对象
        BufferedWriter bw = null;
        try {

            bw = new BufferedWriter(new FileWriter("src/main/java/com/iorn/Demo03处理流/p1缓冲流/字符缓冲流/out.txt"),1024);
            // 写出数据
            bw.write("尚");
            // 写出换行
            bw.newLine();
            bw.write("硅");
            bw.newLine();
            bw.write("谷");
            bw.newLine();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (bw != null) {
                bw.close();
            }
        }

    }
}
