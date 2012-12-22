package com.github.pushman.testini.configuration

import com.github.pushman.testini.runner.TestRunner
import org.junit.runners.model.TestClass

abstract class TestRunnerConfigurationBase(val testClass: TestClass) extends
TestRunner with
TestClassProvider with
TestRunnerTestMethodProvider with
TestRunnerTestDescriptionProvider with
TestRunnerValidator with
TestRunnerMethodInvokerProvider with
RunningTestContextProvider {
  this: TestClassProvider with TestCasesProvider =>
}