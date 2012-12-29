package com.github.pushman.testini.complex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ComplexJUnitTest {

    private final Complex input;
    private final int factor;
    private final Complex expected;

    @Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Complex(0, 0), 0, new Complex(0, 0)},
                {new Complex(1, 2), 2, new Complex(2, 4)},
                {new Complex(1, 2), 0, new Complex(0, 0)}
        });
    }

    public ComplexJUnitTest(Complex input, int factor, Complex expected) {
        this.input = input;
        this.factor = factor;
        this.expected = expected;
    }

    @Test
    public void testMultiply() {
        Complex result = input.multiply(factor);
        assertEquals(expected.getReal(), result.getReal());
        assertEquals(expected.getImaginary(), result.getImaginary());
    }
}