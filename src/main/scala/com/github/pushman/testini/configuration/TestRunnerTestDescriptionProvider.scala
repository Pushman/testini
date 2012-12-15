package com.github.pushman.testini.configuration

import com.github.pushman.testini.descriptions.TestDescriptionProvider
import com.github.pushman.testini.runner.{ParameterisedRunningTestContext, NoArgRunningTestContext, RunningTestContext, TestRunner}
import org.junit.runners.model.FrameworkMethod

trait TestRunnerTestDescriptionProvider {
  this: TestRunner with TestSuiteContext with TestClassProvider =>

  override def suiteDescription = descriptionProvider.describeTest(testCases)

  override def methodDescription(method: FrameworkMethod) =
    descriptionProvider.describeMethod(method)

  override def childDescription(context: RunningTestContext) =
    context match {
      case NoArgRunningTestContext(testCase) =>
        descriptionProvider.describeMethod(testCase.method)
      case ParameterisedRunningTestContext(testCase, testKit) =>
        descriptionProvider.describeTestKit(testCase, testKit)
    }

  protected val descriptionProvider: TestDescriptionProvider =
    new TestDescriptionProvider(testClass)
}