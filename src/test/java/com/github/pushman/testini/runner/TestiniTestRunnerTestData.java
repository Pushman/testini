package com.github.pushman.testini.runner;

import com.github.pushman.testini.data.TestKit;
import com.github.pushman.testini.xml.data.TestKitData;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@SuppressWarnings("UnusedDeclaration")
public class TestiniTestRunnerTestData {

    public List<? extends TestKit> lesserParameters() {
        return newArrayList(
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
