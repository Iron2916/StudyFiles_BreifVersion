package com.iorn.Demo01File.p3面试题.exer2;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

/**
 * ClassName: Exer02
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 16:01
 * @Version 1.0
 */
public class Exer02 {
    /*
    * 判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
    * */
    @Test
    public void test1(){
        File dir = new File("F:\\10-图片");

        //方式1：
//        String[] listFiles = dir.list();
//        for(String s : listFiles){
//            if(s.endsWith(".jpg")){
//                System.out.println(s);
//            }
//        }

        //方式2：
        File file = new File("C:\\Users\\Iron\\Pictures\\Saved Pictures");
        String[] list = file.list(new FilenameFilter() {    // 此处用了FileName过滤器
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg");
            }
        });
        for (String s : list) {
            System.out.println(s);
        }
    }
}
