package com.github.pushman.testini.presentation;

import com.github.pushman.testini.Parameterised;
import com.github.pushman.testini.runner.TestiniRunner;
import com.github.pushman.testini.testKits.TestKit;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts.$;
import static com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts.$ign;
import static org.junit.Assert.assertEquals;

@RunWith(TestiniRunner.class)
public class ComplexTestiniTest {

    @Test
    @Parameterised(method = "multiplyData")
    public void testMultiply(Complex input, int factor, Complex expected) {
        Complex result = input.multiply(factor);
        assertEquals(result.getReal(), expected.getReal());
        assertEquals(result.getImaginary(), expected.getImaginary());
    }

    public TestKit[] multiplyData() {
        return $(
                $(new Complex(0, 0), 0, new Complex(0, 0)),
                $ign(new Complex(1, -2), 100, new Complex(0, 0))
        );
    }
}