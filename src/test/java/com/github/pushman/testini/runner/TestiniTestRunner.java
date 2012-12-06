package com.github.pushman.testini.runner;

import com.github.pushman.testini.Parameterised;
import com.github.pushman.testini.testKits.TestKit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts.$;
import static com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts.$ign;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniRunner.class)
public class TestiniTestRunner {

    @Test
    public void testMarker() {
    }

    @Test
    @Parameterised
    public void shouldConvertToUppercase(String actual, String expected) {
        assertThat(actual.toUpperCase()).isEqualTo(expected);
    }

    public TestKit[] parametersForShouldConvertToUppercase() {
        return $(
                $("Hello, world", "HELLO, WORLD"),
                $("Foo", "FOO")
        );
    }

    @Test
    @Parameterised(method = "upperToLower")
    public void shouldConvertToLowercase(String actual, String expected) {
        assertThat(actual.toLowerCase()).isEqualTo(expected);
    }

    public TestKit[] upperToLower() {
        return $(
                $("Hello, world", "hello, world"),
                $("Foo", "foo"),
                $ign("actually", "is ignored")
        );
    }

    @Test
    @Parameterised(source = TestiniTestRunnerTestData.class)
    public void shouldCompare(String left, String right, Integer expected) {
        assertThat(Math.round(Math.signum(left.compareTo(right)))).isEqualTo(expected);
    }

    @Test
    @Ignore
    @Parameterised
    public void shouldCompareIgnoringCase(String left, String right, Integer expected) {
        assertThat(left.compareToIgnoreCase(right)).isEqualTo(expected);
    }
}