package com.iron.Demo02_JunitConverage;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;

class ScoreDemoTest {

    @Test
    void scoreLevel() {

        ScoreDemo scoreDemo = new ScoreDemo();
        assertEquals("弱",scoreDemo.scoreLevel(52));
    }

    @Test
    void scoreLevel2() {

        ScoreDemo scoreDemo = new ScoreDemo();
        assertEquals("差",scoreDemo.scoreLevel(66));
    }

    @Test
    void scoreLevel3() {

        ScoreDemo scoreDemo = new ScoreDemo();
        assertEquals("中",scoreDemo.scoreLevel(77));
    }
}