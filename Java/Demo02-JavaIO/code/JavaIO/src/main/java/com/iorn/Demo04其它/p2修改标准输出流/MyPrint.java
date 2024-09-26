package com.iorn.Demo04其它.p2修改标准输出流;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 修改标准输出到文件，而不是命令行。
 */
public class MyPrint {

    @Test
    public void TestMyPrint() {

        final File file = new File("src/main/java/com/iorn/Demo04其它/p2修改标准输出流/output.txt");

        PrintStream printStream = null;
        try {
            printStream = new PrintStream(file);
            System.setOut(printStream);
            System.out.println("------------------------------------------");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            printStream.close();
        }
    }
}
