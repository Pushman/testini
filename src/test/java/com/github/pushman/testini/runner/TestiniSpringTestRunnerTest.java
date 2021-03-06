package com.github.pushman.testini.runner;

import com.github.pushman.testini.Parameterised;
import com.github.pushman.testini.XmlParametersConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniRunner.class)
@XmlParametersConfiguration
public class TestiniSpringTestRunnerTest {

    @Test
    public void testMarker() {
    }

    @Test
    @Parameterised
    public void shouldConvertToUppercase(String actual, String expected) {
        assertThat(actual.toUpperCase()).isEqualTo(expected);
    }

    @Test
    @Parameterised
    public void shouldConvertToLowercase(String actual, String expected) {
        assertThat(actual.toLowerCase()).isEqualTo(expected);
    }
}