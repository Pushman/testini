package com.github.pushman.testini.configuration.providers.spring

import org.junit.runners.model.TestClass
import com.github.pushman.testini.testCases.providers.spring.SpringTestCasesProvider
import com.github.pushman.testini.configuration.{TestClassProvider, TestCasesProvider, TestRunnerConfigurationBase}
import com.github.pushman.testini.testCases.TestCase
import com.github.pushman.testini.runner.GenericTestiniTestRunner.TestRunnerBuilder

object SpringTestRunnerConfiguration extends TestRunnerBuilder {

  def apply(testClass: TestClass) =
    new SpringTestRunnerConfiguration(testClass)
}

class SpringTestRunnerConfiguration(testClass: TestClass)
  extends TestRunnerConfigurationBase(testClass) with TestCasesProvider {

  override def testCases: Iterable[TestCase] =
    SpringTestCasesProvider(testClass).testCases
}