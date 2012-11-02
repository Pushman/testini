package com.github.pushman.testini.runner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniSpringTestRunner.class)
public class TestiniSpringTestRunnerTest {

    @Test
    public void testMarker() {
    }

    @Test
    public void shouldConvertToUppercase(String actual, String expected) {
        assertThat(actual.toUpperCase()).isEqualTo(expected);
    }

    @Test
    public void shouldConvertToLowercase(String actual, String expected) {
        assertThat(actual.toLowerCase()).isEqualTo(expected);
    }
}