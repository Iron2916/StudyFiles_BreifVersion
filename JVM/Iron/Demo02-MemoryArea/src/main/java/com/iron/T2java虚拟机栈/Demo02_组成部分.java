package com.iron.T2java虚拟机栈;

/**
 * 局部变量表，操作数栈，帧数据
 */
public class Demo02_组成部分 {
    /**
     * double 和 long 占用两个插槽，其他的类型占用一个插槽
     */
    public static void test1(){
        int i = 0;
        long j = 1;
    }

    /**
     * 实例方法中0号位置存放的是 this
     */
    public void test2(){
        int i = 0;
        long j = 1;
    }

    /**
     * 方法参数也会保存在局部变量表中，其顺序于方法中参数定义的顺序一致
     * @param k
     * @param m
     */
    public void test3(int k,int m){
        int i = 0;
        long j = 1;
    }

    /**
     *思考：演示插槽可以被重复进行使用，即失效的局部变量可以被重复覆盖，这里插槽占用深度为6
     * @param k
     * @param m
     */
    public void test4(int k,int m){
        {
            int a = 1;
            int b = 2;
        }
        {
            int c = 1;
        }
        int i = 0;
        long j = 1;
    }

    /**
     * 测试操作数栈：最大深度为2，栈最大深度为 0 0 1(声明0常量，将0赋值到局部变量表第一个位置，将局部变量表第一个位置(0)取出放入操作数栈，将1放入操作数栈，取出操作数栈前两个数进行相加)
     */
    public void test5(){
        int i = 0;
        int j = i + 1;
    }

    /**
     * 测试栈帧
     */
    public static void test6()  {
        int i = 0;
        try{
            i = 1;
        }catch (Exception e){
            i = 2;
        }

    }
}