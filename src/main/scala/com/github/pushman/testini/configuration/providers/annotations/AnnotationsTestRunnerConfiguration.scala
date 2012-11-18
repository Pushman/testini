package com.github.pushman.testini.configuration.providers.annotations

import org.junit.runners.model.TestClass
import com.github.pushman.testini.configuration.TestRunnerConfigurationBase
import com.github.pushman.testini.testCases.providers.annotations.AnnotationsTestCasesProvider
import com.github.pushman.testini.testCases.TestCase
import com.github.pushman.testini.runner.GenericTestiniTestRunner.TestRunnerBuilder

object AnnotationsTestRunnerConfiguration extends TestRunnerBuilder {

  def apply(testClass: TestClass) =
    new AnnotationsTestRunnerConfiguration(testClass)
}

class AnnotationsTestRunnerConfiguration(val testClass: TestClass)
  extends TestRunnerConfigurationBase {

  def testCases: Iterable[TestCase] = AnnotationsTestCasesProvider(testClass).testCases
}