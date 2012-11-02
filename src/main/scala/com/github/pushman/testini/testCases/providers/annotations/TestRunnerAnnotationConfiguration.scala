package com.github.pushman.testini.testCases.providers.annotations

import org.junit.runners.model.TestClass
import com.github.pushman.testini.data.TestCase
import com.github.pushman.testini.runner.{TestRunner, TestRunnerTestCaseProvider, TestRunnerAbstractConfiguration}

class TestRunnerAnnotationConfiguration(val testClass: TestClass)
  extends TestRunnerAbstractConfiguration with TestRunnerAnnotationTestCaseProvider

trait TestRunnerAnnotationTestCaseProvider extends TestRunnerTestCaseProvider {
  this: TestRunner =>

  protected def testCases: Seq[TestCase] = DefaultAnnotationTestCaseProvider.testCases(testClass)
}