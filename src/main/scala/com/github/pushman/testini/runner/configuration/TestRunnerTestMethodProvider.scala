package com.github.pushman.testini.runner.configuration

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.data.TestCase
import com.github.pushman.testini.runner.TestRunner

trait TestRunnerTestMethodProvider {
  this: TestRunner with TestSuiteContext =>

  override def testMethods: Iterable[FrameworkMethod] = for {
    testCase <- testCases
    methods <- extractMethods(testCase)
  } yield methods

  private def extractMethods(testCase: TestCase): Seq[FrameworkMethod] =
    if (testCase.isParameterised)
      List.fill(testCase.kits.size)(testCase.method)
    else
      List(testCase.method)
}