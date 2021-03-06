package com.github.pushman.testini.runner

import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.{TestClass, Statement, FrameworkMethod}

import org.junit.runner.Description
import java.util
import scala.collection.JavaConversions._
import org.junit.runner.notification.RunNotifier

object GenericTestiniTestRunner {
  type TestRunnerBuilder = (TestClass) => TestRunner
}

import GenericTestiniTestRunner._

class GenericTestiniTestRunner(clazz: Class[_], testRunnerBuilder: TestRunnerBuilder)
  extends BlockJUnit4ClassRunner(clazz) {

  lazy val testRunner = testRunnerBuilder(getTestClass)

  var currentlyRunningTestContext: RunningTestContext = null

  override def computeTestMethods: util.List[FrameworkMethod] =
    testRunner.testMethods.toList

  override def getDescription: Description = {
    testRunner.suiteDescription
  }

  override def validateTestMethods(errors: util.List[Throwable]) {
    errors.addAll(testRunner.validate)
  }

  override def runChild(method: FrameworkMethod, notifier: RunNotifier) {
    currentlyRunningTestContext = testRunner.nextRunningTestContext(method)
    val description: Description = testRunner.childDescription(currentlyRunningTestContext)

    if (isIgnored(currentlyRunningTestContext))
      notifier.fireTestIgnored(description)
    else
      runLeaf(methodBlock(method), description, notifier)
  }

  def isIgnored(context: RunningTestContext): Boolean =
    context match {
      case NoArgRunningTestContext(testCase) =>
        testCase.isIgnored
      case ParameterisedRunningTestContext(testCase, testKit) =>
        testCase.isIgnored || testKit.isIgnored
    }

  override def methodInvoker(method: FrameworkMethod, testTarget: Any): Statement =
    testRunner.methodInvoker(currentlyRunningTestContext)(testTarget)
}