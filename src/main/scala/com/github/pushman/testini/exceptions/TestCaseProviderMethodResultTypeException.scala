package com.github.pushman.testini.exceptions

import java.lang.reflect.Method

class TestCaseProviderMethodResultTypeException(val method: Method, val expectedTypes: Seq[Class[_]])
  extends RuntimeException("Method " + method.getName + " should have return type of: " + expectedTypes) {
}
