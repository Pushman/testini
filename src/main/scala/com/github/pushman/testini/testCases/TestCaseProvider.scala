package com.github.pushman.testini.testCases

import com.github.pushman.testini.data.{TestKit, TestCase}
import org.junit.runners.model.{FrameworkMethod, TestClass}
import org.junit.Test
import scala.collection.JavaConversions._
import com.github.pushman.testini.runner.configuration.TestClassProvider


trait TestCaseProvider {
  def testCases: Seq[TestCase]
}

trait ParameterisedTestCaseProvider extends TestCaseProvider with TestClassProvider {

  override def testCases =
    testClass.getAnnotatedMethods(classOf[Test]).map(createTestCase)

  def createTestCase(method: FrameworkMethod) =
    if (method.getMethod.getParameterTypes.length > 0)
      ParameterisedTestCase(method, computeTestKits(method).getOrElse(noTestKitsError(method)))
    else
      NoArgTestCase(method)

  def computeTestKits(method: FrameworkMethod): Option[Seq[TestKit]]

  def noTestKitsError(method: FrameworkMethod) =
    throw new IllegalArgumentException("Cannot find any TestKits for " + method.getMethod)
}

case class TestCaseProviderImpl(testClass: TestClass, testKitsProviders: Seq[TestKitProvider]) extends ParameterisedTestCaseProvider {

  import com.github.pushman.testini.util.TraversableUtils._

  override def computeTestKits(method: FrameworkMethod): Option[Seq[TestKit]] =
    testKitsProviders.findSome(_.provideTestKits(method))
}

case class NoArgTestCase(method: FrameworkMethod) extends TestCase {
  def kits = List.empty

  def isParameterised = false
}

case class ParameterisedTestCase(method: FrameworkMethod, kits: Seq[TestKit]) extends TestCase {
  def isParameterised = true
}