package com.iorn.Demo02节点流.p2字节节点流;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class 实现文件复制并发现问题 {

    /**
     * 实现文件复制（这里会造成乱码问题，因为FileOutputStream 不支持编码格式，所以得引入处理流 OutputStreamWriter 设置编码格式
     */
    @Test
    public void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File file1 = new File("src/main/java/com/iorn/Demo02节点流/p2字节节点流/input.txt");
            File file2 = new File("src/main/java/com/iorn/Demo02节点流/p2字节节点流/copy.txt");
            fis = new FileInputStream(file1);
            fos = new FileOutputStream(file2);

            int len;
            byte[] buffer = new byte[1024];
            while((len = fis.read(buffer)) != -1){

                for(int i = 0;i < len;i++){
                    buffer[i] = (byte) (buffer[i]);
                }

                fos.write(buffer,0,len);

            }


            System.out.println("复制");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
