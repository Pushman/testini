package com.github.pushman.testini.configuration

import com.github.pushman.testini.descriptions.TestDescriptionProvider
import com.github.pushman.testini.runner.{ParameterisedRunningTestContext, NoArgRunningTestContext, RunningTestContext, TestRunner}
import org.junit.runners.model.FrameworkMethod

trait TestRunnerTestDescriptionProvider {
  this: TestCasesProvider with TestClassProvider =>

  def suiteDescription = descriptionProvider.describeTest(testCases)

  def childDescription(context: RunningTestContext) =
    context match {
      case NoArgRunningTestContext(testCase) =>
        descriptionProvider.describeMethod(testCase.method)
      case ParameterisedRunningTestContext(testCase, testKit) =>
        descriptionProvider.describeTestKit(testCase, testKit)
    }

  protected val descriptionProvider: TestDescriptionProvider =
    new TestDescriptionProvider(testClass)
}