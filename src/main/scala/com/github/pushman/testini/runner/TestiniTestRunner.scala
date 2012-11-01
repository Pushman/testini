package com.github.pushman.testini.runner

import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.{Statement, FrameworkMethod}

import org.junit.runner.Description
import java.util
import scala.collection.JavaConversions._
import com.github.pushman.testini.testCases.DefaultAnnotationTestCaseProvider

class TestiniTestRunner(clazz: Class[_]) extends BlockJUnit4ClassRunner(clazz) {

  lazy val testRunner: AbstractTestRunner = new TestRunner with TestRunnerValidator
    with TestRunnerTestDescriptionProvider with TestRunnerTestMethodProvider
    with TestRunnerMethodInvokerProvider with DefaultAnnotationTestCaseProvider {
    def testClass = getTestClass
  }

  override def computeTestMethods: util.List[FrameworkMethod] = testRunner.testMethods.toList

  override def getDescription: Description = testRunner.suiteDescription

  override def validateTestMethods(errors: util.List[Throwable]) {
    errors.addAll(testRunner.validate)
  }

  override def describeChild(method: FrameworkMethod) =
    testRunner.childDescription(method)

  override def methodInvoker(method: FrameworkMethod, testTarget: Any): Statement =
    testRunner.methodInvoker(method)(testTarget)
}