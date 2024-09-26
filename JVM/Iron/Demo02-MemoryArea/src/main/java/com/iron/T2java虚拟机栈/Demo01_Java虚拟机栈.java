package com.iron.T2java虚拟机栈;

/**
 * debug测试局部变量表的方式
 */
public class Demo01_Java虚拟机栈 {

    public static void main(String[] args) {

        long i = 0;
        int j = 1;
        study();
    }

    public static void study() {

        int k = 2;
        eat();
        sleep();
    }

    private static void sleep() {

        int l = 4;
        System.out.println("睡觉");
    }

    private static void eat() {

        int m = 3;
        System.out.println("吃饭");
    }
}
