package com.github.pushman.testini.configuration.providers.spring

import org.junit.runners.model.TestClass
import com.github.pushman.testini.testCases.providers.spring.SpringTestCasesProvider
import com.github.pushman.testini.configuration.TestRunnerConfigurationBase
import com.github.pushman.testini.testCases.TestCase

class SpringTestRunnerConfiguration(val testClass: TestClass) extends TestRunnerConfigurationBase {

  override def testCases: Iterable[TestCase] = SpringTestCasesProvider(testClass).testCases
}