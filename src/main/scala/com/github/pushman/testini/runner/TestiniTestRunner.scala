package com.github.pushman.testini.runner

import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.{TestClass, Statement, FrameworkMethod}

import org.junit.runner.Description
import java.util
import scala.collection.JavaConversions._
import com.github.pushman.testini.testCases.providers.annotations.AnnotationsTestRunnerConfiguration

class TestiniTestRunner(clazz: Class[_], testRunnerBuilder: (TestClass) => TestRunner)
  extends BlockJUnit4ClassRunner(clazz) {

  def this(clazz: Class[_]) = this(clazz, new AnnotationsTestRunnerConfiguration(_: TestClass))

  lazy val testRunner = testRunnerBuilder(getTestClass)

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