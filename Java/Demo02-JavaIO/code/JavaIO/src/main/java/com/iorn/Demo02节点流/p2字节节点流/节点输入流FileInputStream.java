package com.iorn.Demo02节点流.p2字节节点流;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 一般是用来u处理超文本文件，超文本即非文本文件，比如视频，音频等。
public class 节点输入流FileInputStream {

    /**
     *测试不同字节输入流
     */
    @Test
    public void TestFileInputStream() {

        // 第一步
        final File file = new File("src/main/java/com/iorn/Demo02节点流/p1字符节点流/input.txt");

        FileInputStream inputStream = null;
        try {

            // 第二步
            inputStream = new FileInputStream(file);

            int data;
            while ((data = inputStream.read()) != -1) {

                System.out.print((char) data);  // 因为每次都只能读取一个字节，但是汉字在 UTF-8 下为三个字节，GBK为两个字节，所以乱码。
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (inputStream != null) {

                try {
                    // 第三步
                    inputStream.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *测试字节输入流Buffer
     */
    @Test
    public void TestFileInputStreamByBuffer() {

        // 第一步
        final File file = new File("src/main/java/com/iorn/Demo02节点流/p2字节节点流/input.txt");

        FileInputStream inputStream = null;
        final byte[] buffer = new byte[3];              // utf-8 汉字占三个字节，gbk 汉字占两个字节
        try {

            // 第二步
            inputStream = new FileInputStream(file);

            int len;
            while ((len = inputStream.read(buffer)) != -1) {

                System.out.println(new String(buffer));
            }

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (inputStream != null) {

                try {
                    // 第三步
                    inputStream.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }


}
