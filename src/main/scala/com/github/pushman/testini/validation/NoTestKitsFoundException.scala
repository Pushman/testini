package com.github.pushman.testini.validation

import com.github.pushman.testini.testCases.TestCase

case class NoTestKitsFoundException(testCase: TestCase)
  extends RuntimeException("No test kits found for parameterized method " + testCase.method.getMethod.getName) {
}
