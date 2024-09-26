package com.iron.T1程序计数器;

public class Demo01_程序计数器 {

    /**
     * 通过观察代码的执行流程，可以看出程序计数在代码开始执行之前就记录着每一行的内存地址，每一条字节码独有拥有一个内存地址。进行逻辑判断通过此进行跳跃
     * @param args
     */
    public static void main(String[] args) {

        int i = 0;
        if (i == 0) {

            i--;
        }
        i++;
    }
}
