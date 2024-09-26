package com.iorn.Demo04其它.p3Scanner;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class TestScanner {

    @Test
    public void test01() throws IOException {
        final Scanner input = new Scanner(System.in);
        PrintStream ps = new PrintStream("src/main/java/com/iorn/Demo04其它/p3Scanner/output.txt");
        while(true){
            System.out.print("请输入一个单词：");
            String str = input.nextLine();
            if("stop".equals(str)){
                break;
            }
            ps.println(str);
        }

        ps.println();
        input.close();
        ps.close();
    }
    
    @Test
    public void test2() throws IOException {
        Scanner input = new Scanner(new FileInputStream("src/main/java/com/iorn/Demo04其它/p3Scanner/output.txt"));
        while(input.hasNextLine()){
            String str = input.nextLine();
            System.out.println(str);
        }
        input.close();
    }
}