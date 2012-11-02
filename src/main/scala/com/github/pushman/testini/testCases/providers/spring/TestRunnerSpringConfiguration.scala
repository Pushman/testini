package com.github.pushman.testini.testCases.providers.spring

import org.junit.runners.model.TestClass
import com.github.pushman.testini.runner.{TestRunnerAbstractConfiguration, TestRunner, TestRunnerTestCaseProvider}

class TestRunnerSpringConfiguration(val testClass: TestClass)
  extends TestRunnerAbstractConfiguration with TestRunnerSpringTestCaseProvider

trait TestRunnerSpringTestCaseProvider extends TestRunnerTestCaseProvider {
  this: TestRunner =>

  override def testCases = DefaultSpringTestCaseProvider.testCases(testClass)
}
