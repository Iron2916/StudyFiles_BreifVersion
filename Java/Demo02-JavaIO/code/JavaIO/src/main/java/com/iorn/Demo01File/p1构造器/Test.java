package com.iorn.Demo01File.p1构造器;

import java.io.File;

public class Test {

    public static void main(String[] args) {

        // ============================ part1 构造器 =====================================

        final File file1 = new File("E:\\a.txt");                                   // 绝对路径
        final File file2 = new File("src/main/java/com/iorn/Demo01File/b.txt");     // 相对路径：相对模块
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file2.getAbsoluteFile());

        final File file3 = new File("src/main/java/com/iorn/Demo01File/p", "c.txt");   // p一定是目录，c.txt可以是目录可以是文件
        final File file4 = new File("src/main/java/com/iorn/Demo01File/p", "p2");
        System.out.println(file3.getAbsoluteFile());
        System.out.println(file4.exists());
    }
}
