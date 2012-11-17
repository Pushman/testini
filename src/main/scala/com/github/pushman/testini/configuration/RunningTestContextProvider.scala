package com.github.pushman.testini.configuration

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.testKits.TestKit
import com.github.pushman.testini.runner.{NoArgRunningTestContext, ParameterisedRunningTestContext, RunningTestContext}
import com.github.pushman.testini.testCases.TestCase
import com.github.pushman.testini.utils.TraversableUtils

trait RunningTestContextProvider {
  this: RunningTestContextProvider with TestSuiteContext =>

  def nextRunningTestContext(method: FrameworkMethod): RunningTestContext =
    testCasesIterator(method).next()

  protected val testCasesIterator: Map[FrameworkMethod, Iterator[RunningTestContext]] = {
    for (testCase <- testCases)
    yield testCase.method -> contextsForTestCase(testCase)
  }.toMap

  def contextsForTestCase(testCase: TestCase): Iterator[RunningTestContext] =
    if (testCase.isParameterised)
      TraversableUtils.iterator(testCase.kits, testKitContext(testCase))
    else
      Iterator.single(NoArgRunningTestContext(testCase))

  def testKitContext(testCase: TestCase)(testKit: TestKit) =
    ParameterisedRunningTestContext(testCase, testKit)
}