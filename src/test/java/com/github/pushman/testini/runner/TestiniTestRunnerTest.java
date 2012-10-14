package com.github.pushman.testini.runner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniTestRunner.class)
public class TestiniTestRunnerTest {

    @Test
    public void testMarker() {
    }

    @Test
    public void shouldConvertToUppercase(String actual, String expected) {
        assertThat(actual.toUpperCase()).isEqualTo(expected);
    }
}
