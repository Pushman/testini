package com.github.pushman.testini.configuration

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.runner.TestRunner
import com.github.pushman.testini.testCases.TestCase

trait TestRunnerTestMethodProvider {
  this: TestRunner with TestSuiteContext =>

  override def testMethods: Iterable[FrameworkMethod] = for {
    testCase <- testCases
    methods <- extractMethods(testCase)
  } yield methods

  private def extractMethods(testCase: TestCase): Seq[FrameworkMethod] =
    if (testCase.isParameterised && !testCase.isIgnored)
      List.fill(testCase.kits.size)(testCase.method)
    else
      List(testCase.method)
}