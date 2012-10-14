package com.github.pushman.testini.runner

import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runner.notification.RunNotifier

import org.junit.runner.Description
import com.github.pushman.testini.descriptions.TestDescriptionProvider
import java.util
import scala.collection.JavaConversions._
import com.github.pushman.testini.validation.TestCaseValidator
import com.github.pushman.testini.data.TestCase.extendTestCasesSeq
import com.github.pushman.testini.testCases.TestCaseProvider
import statements.ParameterisedStatement
import com.github.pushman.testini.methods.ParameterisedMethod

class TestiniTestRunner(clazz: Class[_]) extends BlockJUnit4ClassRunner(clazz) {

  private lazy val testCaseValidator = new TestCaseValidator
  private lazy val descriptionProvider = new TestDescriptionProvider(getTestClass)
  private lazy val testCaseProvider = new TestCaseProvider(getTestClass)

  private lazy val testCases = testCaseProvider.testCases

  override def computeTestMethods = testCases.extractFrameworkMethods

  override def getDescription: Description =
    descriptionProvider.describeTest(testCases)

  override def validateTestMethods(errors: util.List[Throwable]) {
    errors.addAll(testCaseValidator.validateAll(testCases))
  }

  override def runChild(method: FrameworkMethod, notifier: RunNotifier) {
    val testCase = testCases.findMethod(method)
    val description = if (testCase.isParameterised) {
      descriptionProvider.describeTestKit(testCase.kits.get(0))
    } else {
      descriptionProvider.describeMethod(method)
    }
    runLeaf(methodBlock(method), description, notifier)
  }

  override def methodInvoker(method: FrameworkMethod, testTarget: Any) = {
    val testCase = testCases.findMethod(method)
    if (testCase.isParameterised) {
      new ParameterisedStatement(new ParameterisedMethod(method, testCase.kits.get(0)), testTarget)
    } else {
      super.methodInvoker(method, testTarget)
    }
  }
}
