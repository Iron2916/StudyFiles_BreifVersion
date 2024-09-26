package com.iorn.Demo01File.p2获取文件基本信息和操作文件;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {



        final File file = new File("E:\\学习学习\\Java学习资料\\java\\资料\\01_课件与电子教材\\01_课件与电子教材");

        System.out.println("================================= part1 判断方法 ========================");
        /*
        判断功能的方法
        - `public boolean exists()` ：此File表示的文件或目录是否实际存在。
        - `public boolean isDirectory()` ：此File表示的是否为目录。
        - `public boolean isFile()` ：此File表示的是否为文件。
        - public boolean canRead() ：判断是否可读
        - public boolean canWrite() ：判断是否可写
        - public boolean isHidden() ：判断是否隐藏
        * */
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.isHidden());

        System.out.println("================================= part2 获取文件信息 ========================");
        /*
        获取文件和目录基本信息
        * public String getName() ：获取名称
        * public String getPath() ：获取路径
        * `public String getAbsolutePath()`：获取绝对路径
        * public File getAbsoluteFile()：获取绝对路径表示的文件
        * `public String getParent()`：获取上层文件目录路径。若无，返回null
        * public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
        * public long lastModified() ：获取最后一次的修改时间，毫秒值
        * */
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.lastModified());


        System.out.println("================================= part3 文件下一级 ========================");
        /*
         列出目录的下一级
         * public String[] list() ：返回一个String数组，表示该File目录中的所有子文件或目录。
         * public File[] listFiles() ：返回一个File数组，表示该File目录中的所有的子文件或目录。
         * */

        final String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }

        final File[] files = file.listFiles();
        for (File f : files) {

            if (f.isDirectory()) {
                System.out.println(f.getName());
            }
        }

        System.out.println("================================= part4 创建和删除功能 ========================");
        /*
        创建、删除功能
        - `public boolean createNewFile()` ：创建文件。若文件存在，则不创建，返回false。
        - `public boolean mkdir()` ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
        - `public boolean mkdirs()` ：创建文件目录。如果上层文件目录不存在，一并创建。
        - `public boolean delete()` ：删除文件或者文件夹
          删除注意事项：① Java中的删除不走回收站。② 要删除一个文件目录，请注意该文件目录内不能包含文件或者文件目录。
        * */

        final File file1 = new File("E:/hello.txt");
        final boolean newFile = file1.createNewFile();
        if (newFile) System.out.println("创建文件成功"); else System.out.println("创建文件失败");

        final File file2 = new File("E:/hello");
        final boolean mkdirs = file2.mkdirs();
        if (mkdirs) System.out.println("创建目录成功"); else System.out.println("创建目录失败");

        final boolean delete = file1.delete();
        if (delete) System.out.println("删除文成功"); else System.out.println("删除文件失败");

        final boolean delete1 = file2.delete();
        if (delete1) System.out.println("删除目录成功"); else System.out.println("删除文件失败");

        System.out.println("================================= part4 重命名 ========================");
        /*
        File类的重命名功能
        - public boolean renameTo(File dest):把文件重命名为指定的文件路径。

        举例：
        file1.renameTo(file2):要想此方法执行完，返回true。要求：
           file1必须存在，且file2必须不存在，且file2所在的文件目录需要存在。
        * */
        final File file3 = new File("E:/a.txt");
        final File file4 = new File("E:/b.txt");
        final boolean b = file3.renameTo(file4);
        if (b) System.out.println("重命名成功"); else System.out.println("重命名失败");
    }
}
