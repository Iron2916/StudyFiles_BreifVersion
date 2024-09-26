package com.iorn.Demo01File.p3面试题.exer3;

import org.junit.Test;

import java.io.File;

/**
 * ClassName: Exer03
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 16:08
 * @Version 1.0
 */
public class Exer03 {
    //public void printFileName(File file)  //file可能是文件，也可能是文件目录
    @Test
    public void test1(){
        File file = new File("C:\\Users\\Iron\\Pictures\\test");
        // printFileName(file);
        long size = printFileSize(file);
        System.out.println("size = " + size + "字节");
        deleteAll(file);
    }

    // 打印 目录 或 文件名。
    public void printFileName(File file){
        if(file.isFile()){
            System.out.println(file.getName());
        }else if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                printFileName(f);
            }
        }
    }

    // 获取 目录 或 文件的大小。
    public long printFileSize(File file)
    {
        long len = 0;
        if(file.isFile()){
            len = file.length();
        }else if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                len += printFileSize(f);
            }
        }
        return len;
    }

    // 删除指定文件目录下的所有文件：只有目录为空才能进行删除
    public void deleteAll(File file)
    {
        if (file.isFile())
        {
            file.delete();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteAll(f);
            }
            file.delete();
        }
    }
}
