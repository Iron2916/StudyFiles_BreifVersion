package com.iorn.Demo02节点流.p1字符节点流;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

/**
 * 字符输出流：写出字符到文件构造时使用 系统默认的字符编码 和 默认字节缓冲区
 */
public class 字符输出流Filewrite {

    /**
     * 字符输出流基本使用。
     */
    @Test
    public void TestFileInput() {

        // 第一步
        final File file = new File("src/main/java/com/iorn/Demo02节点流/p1字符节点流/output.txt");

        FileWriter writer = null;
        try {

            // 第二步
            writer = new FileWriter(file);
            writer.write("你好！\n");
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (writer != null) {

                try {
                    // 第三步
                    writer.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }

    }


    @Test
    public void TestOutputByBufferAndAppend() {

        // 第一步
        final File file = new File("src/main/java/com/iorn/Demo02节点流/p1字符节点流/output.txt");

        FileWriter writer = null;
        try {

            // 第二步
            writer = new FileWriter(file, true);
            final char[] arr = "hello china!\n".toCharArray();
            writer.write(arr);
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (writer != null) {

                try {
                    // 第三步
                    writer.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
