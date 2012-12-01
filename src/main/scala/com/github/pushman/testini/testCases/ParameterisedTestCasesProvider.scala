package com.github.pushman.testini.testCases

import org.junit.runners.model.{FrameworkMethod, TestClass}
import org.junit.{Ignore, Test}
import scala.collection.JavaConversions._
import com.github.pushman.testini.testKits.TestKit
import com.github.pushman.testini.Parameterised

abstract class ParameterisedTestCasesProvider(testClass: TestClass) extends TestCasesProvider {

  override def testCases =
    testClass.getAnnotatedMethods(classOf[Test]).map(createTestCase)

  def createTestCase(method: FrameworkMethod) =
    if (Option(method.getMethod.getAnnotation(classOf[Ignore])).isDefined)
      IgnoredTestCase(method)
    else if (Option(method.getMethod.getAnnotation(classOf[Parameterised])).isDefined)
      ParameterisedTestCase(method, computeTestKits(method).getOrElse(noTestKitsError(method)))
    else
      NoArgTestCase(method)

  def computeTestKits(method: FrameworkMethod): Option[Seq[TestKit]]

  def noTestKitsError(method: FrameworkMethod) =
    throw new IllegalArgumentException("Cannot find any TestKits for " + method.getMethod)
}