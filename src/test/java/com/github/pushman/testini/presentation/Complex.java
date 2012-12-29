package com.github.pushman.testini.presentation;

public class Complex {
    private int real;
    private int imaginary;

    public Complex() {
    }

    public Complex(int real, int imaginary) {
        this.setReal(real);
        this.setImaginary(imaginary);
    }

    public Complex multiply(int factor) {
        return new Complex(real * factor, imaginary * factor);
    }

    @Override
    public String toString() {
        return "Complex{" + "real=" + getReal() + ", imaginary=" + getImaginary() + '}';
    }

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public int getImaginary() {
        return imaginary;
    }

    public void setImaginary(int imaginary) {
        this.imaginary = imaginary;
    }
}