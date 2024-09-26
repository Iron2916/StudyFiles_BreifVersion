package com.iron.Demo03_Annotation;

import com.iron.Demo01_Junit.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

class AnotationTest {

    @BeforeAll
    static void beforeClass() {

        System.out.println("BeforeAll�����в��Է�������ǰִ��һ�Σ�ʵ����֮ǰ�ͱ�������Ҫstatic����");
    }
    @AfterAll
    static void afterAll() {

        System.out.println("AfterAll�����в��Է������ù�֮��ִ��һ�Σ�ʵ����֮ǰ�ͱ����أ���Ҫstatic����");
    }

    @BeforeEach
    void beforeeach() {

        System.out.println("BeforeEach��ÿ�����Է���ִ��ǰ���ý��е���ͬ");
    }
    @AfterEach
    void afterEach() {

        System.out.println("afterEach��ÿһ�����Է������ú��ִ�еķ���");
    }

    @Test
    void add() {

        final Annotation calculator = new Annotation();
        assertEquals(4, calculator.add(2, 2));
    }

    @Test
    void minus() {

        final Annotation calculator = new Annotation();
        assertEquals(0, calculator.minus(2, 2));
    }
}