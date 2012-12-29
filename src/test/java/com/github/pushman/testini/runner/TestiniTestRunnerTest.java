package com.github.pushman.testini.runner;

import com.github.pushman.testini.Parameterised;
import com.github.pushman.testini.testKits.TestKit;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import scala.collection.Seq;
import scala.collection.mutable.ArrayBuffer;

import static com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts.$;
import static com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts.$ign;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniRunner.class)
public class TestiniTestRunnerTest {

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
                $("Foo", "FOO"),
                $ign("actually", "is ignored")
        );
    }

    @Test
    @Parameterised(method = "upperToLower")
    public void shouldConvertToLowercase(String actual, String expected) {
        assertThat(actual.toLowerCase()).isEqualTo(expected);
    }

    public Object[] upperToLower() {
        return new Object[]{
                new String[]{"Hello, world", "hello, world"},
                new String[]{"Foo", "foo"}
        };
    }

    @Test
    @Parameterised
    public void shouldComputeStringLength(String string, int expectedLength) {
        assertThat(string.length()).isEqualTo(expectedLength);
    }

    public static Seq<TestKit> parametersForShouldComputeStringLength() {
        ArrayBuffer<TestKit> tests = new ArrayBuffer<TestKit>();
        tests.$plus$eq($("Foo", 3));
        tests.$plus$eq($("Zażółć", 6));
        return tests;
    }

    @Test
    @Parameterised(source = TestiniTestRunnerTestData.class)
    public void shouldCompare(String left, String right, Integer expected) {
        assertThat(Math.round(Math.signum(left.compareTo(right)))).isEqualTo(expected);
    }

    @Test
    @Ignore // brak danych testowych
    @Parameterised
    public void shouldCompareIgnoringCase(String left, String right, Integer expected) {
        assertThat(left.compareToIgnoreCase(right)).isEqualTo(expected);
    }
}