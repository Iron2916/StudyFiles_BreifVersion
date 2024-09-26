package com.iorn.Demo02节点流.p2字节节点流;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

public class 节点输出流FileOutputStream {

    @Test
    public void TestFileOutPutStream() {

        final File file = new File("src/main/java/com/iorn/Demo02节点流/p2字节节点流/output.txt");

        FileOutputStream outputStream = null;
        try {

            outputStream = new FileOutputStream(file);

            final byte[] bytes = "你好".getBytes();
            outputStream.write(bytes, 0 , 3);   // utf-8默认一个汉字三个字节
            outputStream.write(97); // 转换为字符
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (outputStream != null) {

                try {

                    outputStream.close();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }

}
