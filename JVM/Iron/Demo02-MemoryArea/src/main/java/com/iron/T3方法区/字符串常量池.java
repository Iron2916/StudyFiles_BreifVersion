package com.iron.T3方法区;

public class 字符串常量池 {

    public static void main(String[] args) {

        String a = "1";
        String b = "2";
        String c = "12";
//        String d = a + b;
        String d = "1" + "2";
        System.out.println(c == d); //false
    }
}
