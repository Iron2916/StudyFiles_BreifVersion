package com.iron.Demo03_Annotation;

import com.iron.Demo01_Junit.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

class AnotationTest {

    @BeforeAll
    static void beforeClass() {

        System.out.println("BeforeAll：所有测试方法调用前执行一次，实例化之前就被加载需要static修饰");
    }
    @AfterAll
    static void afterAll() {

        System.out.println("AfterAll：所有测试方法调用过之后执行一次，实例化之前就被加载，需要static修饰");
    }

    @BeforeEach
    void beforeeach() {

        System.out.println("BeforeEach：每个测试方法执行前都得进行调用同");
    }
    @AfterEach
    void afterEach() {

        System.out.println("afterEach：每一个测试方法调用后必执行的方法");
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