package com.github.pushman.testini.testCases

import org.junit.runners.model.{FrameworkMethod, TestClass}
import org.junit.Test
import scala.collection.JavaConversions._
import com.github.pushman.testini.testKits.TestKit

abstract class ParameterisedTestCasesProvider(testClass: TestClass) extends TestCasesProvider {

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