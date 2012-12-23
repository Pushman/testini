package com.github.pushman.testini.configuration

import com.github.pushman.testini.runner.TestRunner
import org.junit.runners.model.TestClass
import com.github.pushman.testini.testCases.TestCase

class TestRunnerConfigurationBase(val testClass: TestClass, val testCases: Iterable[TestCase])
  extends TestRunner with
  TestClassProvider with
  TestCasesProvider with
  TestRunnerTestMethodProvider with
  TestRunnerTestDescriptionProvider with
  TestRunnerValidator with
  TestRunnerMethodInvokerProvider with
  RunningTestContextProvider {
}