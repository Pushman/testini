package com.github.pushman.testini.testCases

import com.github.pushman.testini.data.{TestKit, NoArgTestCase, ParameterisedTestCase, TestCase}
import org.junit.runners.model.{FrameworkMethod, TestClass}
import org.junit.Test
import scala.collection.JavaConversions._


trait TestCaseProvider {
  def testCases(testClass: TestClass): Seq[TestCase]
}

trait ParameterisedTestCaseProvider extends TestCaseProvider {

  override def testCases(testClass: TestClass) =
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

case class TestCaseProviderImpl(testKitsProviders: TestKitProvider*) extends ParameterisedTestCaseProvider {

  import com.github.pushman.testini.util.TraversableUtils._

  override def computeTestKits(method: FrameworkMethod): Option[Seq[TestKit]] =
    testKitsProviders.findSome(_.provideTestKits(method))
}