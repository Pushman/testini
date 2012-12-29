package com.github.pushman.testini.complex;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ComplexJUnitParamsTest {

    @Test
    @Parameters(method = "inputData")
    public void testMultiply(Complex input, int factor, Complex expected) {
        Complex result = input.multiply(factor);
        assertEquals(expected.getReal(), result.getReal());
        assertEquals(expected.getImaginary(), result.getImaginary());
    }

    public static Object[] inputData() {
        return $(
                $(new Complex(0, 0), 0, new Complex(0, 0)),
                $(new Complex(1, 2), 2, new Complex(2, 4)),
                $(new Complex(1, 2), 0, new Complex(0, 0))
        );
    }
}