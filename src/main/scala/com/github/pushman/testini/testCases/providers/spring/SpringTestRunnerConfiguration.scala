package com.github.pushman.testini.testCases.providers.spring

import org.junit.runners.model.TestClass
import com.github.pushman.testini.runner.configuration.TestRunnerConfigurationBase
import com.github.pushman.testini.data.TestCase

class SpringTestRunnerConfiguration(val testClass: TestClass) extends TestRunnerConfigurationBase {

  override def testCases: Iterable[TestCase] = DefaultSpringTestCaseProvider.testCases(testClass)
}
