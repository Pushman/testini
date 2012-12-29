package com.github.pushman.testini.presentation;

import com.github.pushman.testini.testKits.TestKit;

import static com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts.$;

@SuppressWarnings("UnusedDeclaration")
public class ComplexTestData {

    public TestKit[] withZeroFactorParameters() {
        Complex zero = new Complex(0, 0);
        return $(
                $(zero, 0, zero),
                $(new Complex(-1, 2), 0, zero),
                $(new Complex(1, 2), 0, zero)
        );
    }

    public TestKit[] multiplyLargeNumbersParameters() {
        return $(
                $(new Complex(0, 0), Integer.MAX_VALUE, new Complex(0, 0)),
                $(new Complex(1, 1), Integer.MAX_VALUE, new Complex(Integer.MAX_VALUE, Integer.MAX_VALUE)),
                $(new Complex(Integer.MIN_VALUE + 1, 0), -1, new Complex(Integer.MAX_VALUE, 0))
        );
    }
    // etc.
}