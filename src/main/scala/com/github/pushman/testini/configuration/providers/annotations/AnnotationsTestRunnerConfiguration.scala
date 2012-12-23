package com.github.pushman.testini.configuration.providers.annotations

import org.junit.runners.model.TestClass
import com.github.pushman.testini.configuration.{TestClassProvider, TestCasesProvider, TestRunnerConfigurationBase}
import com.github.pushman.testini.testCases.{ParameterisedTestCasesProvider, TestCase}
import com.github.pushman.testini.runner.GenericTestiniTestRunner.TestRunnerBuilder
import com.github.pushman.testini.testKits.providers.spring.SpringTestKitsProvider
import com.github.pushman.testini.testKits.providers.annotations.AnnotationsTestKitsProvider

object AnnotationsTestRunnerConfiguration extends TestRunnerBuilder {

  def apply(testClass: TestClass) = {
    new TestRunnerConfigurationBase(testClass, testCasesProvider(testClass).testCases)
  }

  def testCasesProvider(testClass: TestClass) = {
    new ParameterisedTestCasesProvider(testClass, AnnotationsTestKitsProvider())
  }
}