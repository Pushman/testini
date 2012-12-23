package com.github.pushman.testini.configuration.providers.spring

import org.junit.runners.model.TestClass
import com.github.pushman.testini.configuration.TestRunnerConfigurationBase
import com.github.pushman.testini.testCases.ParameterisedTestCasesProvider
import com.github.pushman.testini.runner.GenericTestiniTestRunner.TestRunnerBuilder
import com.github.pushman.testini.testKits.providers.spring.SpringTestKitsProvider

object SpringTestRunnerConfiguration extends TestRunnerBuilder {

  def apply(testClass: TestClass) = {
    new TestRunnerConfigurationBase(testClass, testCasesProvider(testClass).testCases)
  }

  def testCasesProvider(testClass: TestClass) = {
    new ParameterisedTestCasesProvider(testClass, new SpringTestKitsProvider(testClass))
  }
}