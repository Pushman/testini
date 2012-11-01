package com.github.pushman.testini.runner

import org.junit.runners.model.{TestClass, Statement, FrameworkMethod}
import org.junit.runner.Description
import com.github.pushman.testini.data.TestKit
import com.github.pushman.testini.descriptions.TestDescriptionProvider
import com.github.pushman.testini.statements.ParameterisedStatement
import com.github.pushman.testini.data.TestCase
import com.github.pushman.testini.validation.TestCaseValidator
import com.github.pushman.testini.util.TraversableUtils
import org.junit.internal.runners.statements.InvokeMethod
import com.github.pushman.testini.testCases.TestCaseProvider
import collection.immutable.Iterable

trait AbstractTestRunner {

  type MethodInvoker = (Any) => Statement

  def testMethods: Iterable[FrameworkMethod]

  def validate: Iterable[Throwable]

  def suiteDescription: Description

  def childDescription(method: FrameworkMethod): Description

  def methodInvoker(method: FrameworkMethod): (Any) => Statement
}

trait TestRunner extends AbstractTestRunner {

  def testClass: TestClass

  def testCases: Map[FrameworkMethod, TestCase]
}

trait TestRunnerValidator {
  this: TestRunner =>

  private def testCaseValidator = new TestCaseValidator

  override def validate = testCases.values.toList.flatMap(testCaseValidator.validate)
}

trait TestRunnerTestDescriptionProvider {
  this: TestRunner =>

  private def descriptionProvider: TestDescriptionProvider = new TestDescriptionProvider(testClass)

  override def suiteDescription = descriptionProvider.describeTest(testCases.values)

  override def childDescription(method: FrameworkMethod) = methodDescriptions(method).next()

  val methodDescriptions: Map[FrameworkMethod, Iterator[Description]] =
    testCases.transform(descriptionIteratorForTestCase)

  def descriptionIteratorForTestCase(method: FrameworkMethod, testCase: TestCase): Iterator[Description] =
    if (testCase.isParameterised)
      TraversableUtils.iterator(testCase.kits, descriptionProvider.describeTestKit(testCase, _: TestKit))
    else
      Iterator.single(descriptionProvider.describeMethod(method))
}

trait TestRunnerTestMethodProvider {
  this: TestRunner =>

  def testCaseProvider: TestCaseProvider

  override def testCases = testCaseProvider.testCases(testClass).map(tc => (tc.method -> tc)).toMap

  override def testMethods: Iterable[FrameworkMethod] = for {
    (method, testCase) <- testCases
    methods <- extractMethods(method, testCase)
  } yield methods

  private def extractMethods(method: FrameworkMethod, testCase: TestCase): Seq[FrameworkMethod] =
    if (testCase.isParameterised)
      List.fill(testCase.kits.size)(method)
    else
      List(method)
}

trait TestRunnerMethodInvokerProvider {
  this: TestRunner =>

  override def methodInvoker(method: FrameworkMethod) = methodInvokers(method).next()

  val methodInvokers: Map[FrameworkMethod, Iterator[MethodInvoker]] =
    testCases.transform(methodInvokerIteratorForTestCase)

  def methodInvokerIteratorForTestCase(method: FrameworkMethod, testCase: TestCase): Iterator[MethodInvoker] =
    if (testCase.isParameterised)
      TraversableUtils.iterator(testCase.kits, testKitInvoker(method))
    else
      Iterator.single(new InvokeMethod(method, _: Any))

  def testKitInvoker(method: FrameworkMethod)(testKit: TestKit) =
    new ParameterisedStatement(method, testKit, _: Any)
}