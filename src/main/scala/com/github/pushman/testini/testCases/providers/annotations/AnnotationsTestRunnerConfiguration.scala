package com.github.pushman.testini.testCases.providers.annotations

import org.junit.runners.model.TestClass
import com.github.pushman.testini.runner.configuration.TestRunnerConfigurationBase
import com.github.pushman.testini.data.TestCase

class AnnotationsTestRunnerConfiguration(val testClass: TestClass) extends TestRunnerConfigurationBase {

  def testCases: Iterable[TestCase] = AnnotationsTestCaseProvider(testClass).testCases
}