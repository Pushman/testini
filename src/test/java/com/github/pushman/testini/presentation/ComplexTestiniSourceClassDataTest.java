package com.github.pushman.testini.presentation;

import com.github.pushman.testini.Parameterised;
import com.github.pushman.testini.runner.TestiniRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(TestiniRunner.class)
public class ComplexTestiniSourceClassDataTest {

    @Test
    @Parameterised(source = ComplexTestData.class)
    public void testMultiply(Complex input, int factor, Complex expected) {
        Complex result = input.multiply(factor);
        assertEquals(expected.getReal(), result.getReal());
        assertEquals(expected.getImaginary(), result.getImaginary());
    }
}