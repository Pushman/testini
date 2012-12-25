package com.github.pushman.testini.utils.shortcuts;


import com.github.pushman.testini.testKits.TestKit;
import com.github.pushman.testini.xml.data.XmlTestKit;

import java.util.Arrays;

public final class TestCaseShortcuts {

    private TestCaseShortcuts() {
    }

    public static TestKit[] $(TestKit... testKits) {
        return testKits;
    }

    public static TestKit $(Object... data) {
        return new XmlTestKit(Arrays.asList(data));
    }

    public static TestKit $ign(Object... data) {
        return new XmlTestKit(Arrays.asList(data), true);
    }
}