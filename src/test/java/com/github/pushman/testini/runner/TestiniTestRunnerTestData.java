package com.github.pushman.testini.runner;

import com.github.pushman.testini.testKits.TestKit;
import com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts;
import com.github.pushman.testini.xml.data.TestKitData;
import org.junit.Ignore;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@SuppressWarnings("UnusedDeclaration")
@Ignore
public class TestiniTestRunnerTestData {

    public TestKit[] lesserParameters() {
        return TestCaseShortcuts.$(
                new TestKitData(newArrayList("hello, world", "HELLO, WORLD", 1)),
                new TestKitData(newArrayList("foo", "Foo", 1))
        );
    }

    public List<? extends TestKit> equalParameters() {
        return newArrayList(
                new TestKitData(newArrayList("HELLO, WORLD", "HELLO, WORLD", 0)),
                new TestKitData(newArrayList("FOO", "FOO", 0))
        );
    }
}