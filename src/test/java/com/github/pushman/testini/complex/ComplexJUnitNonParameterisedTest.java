package com.github.pushman.testini.complex;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComplexJUnitNonParameterisedTest {

    @Test
    public void testMultiply() {
        Complex input = new Complex(0, 0);
        int factor = 0;
        Complex expected = new Complex(0, 0);
        Complex result = input.multiply(factor);
        assertEquals(expected.getReal(), result.getReal());
        assertEquals(expected.getImaginary(), result.getImaginary());
    }

    @Test
    public void testMultiply2() {
        Complex complex = new Complex(1, -2);
        int factor = 2;
        complex.multiply(factor);
        assertEquals(complex.getReal(), 1);
        assertEquals(complex.getImaginary(), -2);
    }
}