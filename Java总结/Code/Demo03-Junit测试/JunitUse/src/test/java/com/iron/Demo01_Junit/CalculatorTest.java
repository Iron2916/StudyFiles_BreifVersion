package com.iron.Demo01_Junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void add() {

        final Calculator calculator = new Calculator();
        assertEquals(4, calculator.add(2, 2));
    }

    @Test
    void minus() {

        final Calculator calculator = new Calculator();
        assertEquals(0, calculator.minus(2, 2));
    }
}