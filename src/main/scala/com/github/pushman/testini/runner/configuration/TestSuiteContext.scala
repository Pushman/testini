package com.github.pushman.testini.runner.configuration

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.data.TestCase

trait TestSuiteContext {

  def testCaseForMethod(method: FrameworkMethod): TestCase = methodsTestCases(method)

  def testCases: Iterable[TestCase]

  protected val methodsTestCases: Map[FrameworkMethod, TestCase] =
    testCases.map(tc => (tc.method -> tc)).toMap
}