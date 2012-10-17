package com.github.pushman.testini.methods

import com.github.pushman.testini.data.TestCase
import org.junit.runners.model.FrameworkMethod

class TestMethodsProvider {

  def computeTestMethods(testCases: Seq[TestCase]) =
    testCases.flatMap(extractMethod)

  private def extractMethod(testCase: TestCase): Seq[FrameworkMethod] = {
    if (testCase.isParameterised)
      for (i <- testCase.kits) yield testCase.method
    else
      List(testCase.method)
  }
}
