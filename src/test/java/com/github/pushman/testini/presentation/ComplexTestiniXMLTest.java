package com.github.pushman.testini.presentation;

import com.github.pushman.testini.Parameterised;
import com.github.pushman.testini.XmlParametersConfiguration;
import com.github.pushman.testini.runner.TestiniRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(TestiniRunner.class)
@XmlParametersConfiguration(source = "complex/TestData.xml")
public class ComplexTestiniXMLTest {

    @Test
    @Parameterised
    public void testMultiply(Complex input, int factor, Complex expected) {
        Complex result = input.multiply(factor);
        assertEquals(result.getReal(), expected.getReal());
        assertEquals(result.getImaginary(), expected.getImaginary());
    }
}