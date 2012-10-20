package com.github.pushman.testini.runner

import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod

import org.junit.runner.Description
import com.github.pushman.testini.descriptions.TestDescriptionProvider
import java.util
import scala.collection.JavaConversions._
import com.github.pushman.testini.validation.TestCaseValidator
import com.github.pushman.testini.testCases.TestCaseProvider
import com.github.pushman.testini.data.TestCase
import com.github.pushman.testini.methods.TestMethodsProvider

class TestiniTestRunner(clazz: Class[_]) extends BlockJUnit4ClassRunner(clazz) {

  private lazy val testCaseValidator = new TestCaseValidator
  private lazy val descriptionProvider = new TestDescriptionProvider(getTestClass)
  private lazy val testCaseProvider = new TestCaseProvider(getTestClass)
  private lazy val testMethodsProvider = new TestMethodsProvider

  private lazy val testCases = testCaseProvider.testCases

  private lazy val parameterisedTestRunner = new TestiniParameterisedTestRunner(testCases, descriptionProvider)

  override def computeTestMethods =
    testMethodsProvider.computeTestMethods(testCases)

  override val getDescription: Description =
    descriptionProvider.describeTest(testCases)

  override def validateTestMethods(errors: util.List[Throwable]) {
    errors.addAll(testCaseValidator.validateAll(testCases))
  }

  override def describeChild(method: FrameworkMethod) =
    parameterisedTestRunner.descriptionForTestCase(method)

  override def methodInvoker(method: FrameworkMethod, testTarget: Any) = {
    parameterisedTestRunner.methodInvoker(method, testTarget) match {
      case Some(invoker) => {
        parameterisedTestRunner.notifyMethodInvoked(method)
        invoker
      }
      case None => super.methodInvoker(method, testTarget)
    }
  }
}
