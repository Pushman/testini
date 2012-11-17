package com.github.pushman.testini.runner

import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.{TestClass, Statement, FrameworkMethod}

import org.junit.runner.Description
import java.util
import scala.collection.JavaConversions._
import com.github.pushman.testini.configuration.providers.annotations.AnnotationsTestRunnerConfiguration
import org.junit.runner.notification.RunNotifier
import org.junit.Ignore

class TestiniTestRunner(clazz: Class[_], testRunnerBuilder: (TestClass) => TestRunner)
  extends BlockJUnit4ClassRunner(clazz) {

  def this(clazz: Class[_]) =
    this(clazz, new AnnotationsTestRunnerConfiguration(_: TestClass))

  lazy val testRunner = testRunnerBuilder(getTestClass)

  var currentlyRunningTestContext: RunningTestContext = null

  override def computeTestMethods: util.List[FrameworkMethod] =
    testRunner.testMethods.toList

  override def getDescription: Description =
    testRunner.suiteDescription

  override def validateTestMethods(errors: util.List[Throwable]) {
    errors.addAll(testRunner.validate)
  }

  override def runChild(method: FrameworkMethod, notifier: RunNotifier) {
    currentlyRunningTestContext = testRunner.nextRunningTestContext(method)
    val description: Description = describeChild(method)

    if (isIgnored(currentlyRunningTestContext))
      notifier.fireTestIgnored(description)
    else
      runLeaf(methodBlock(method), description, notifier)
  }

  def isIgnored(context: RunningTestContext): Boolean =
    context match {
      case NoArgRunningTestContext(testCase) =>
        testCase.method.getAnnotation(classOf[Ignore]) != null
      case ParameterisedRunningTestContext(testCase, testKit) =>
        testCase.method.getAnnotation(classOf[Ignore]) != null || testKit.ignore
    }

  override def describeChild(method: FrameworkMethod) =
    testRunner.childDescription(currentlyRunningTestContext)

  override def methodInvoker(method: FrameworkMethod, testTarget: Any): Statement =
    testRunner.methodInvoker(currentlyRunningTestContext)(testTarget)
}