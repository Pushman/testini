package com.github.pushman.testini.testCases

import org.junit.runners.model.{FrameworkMethod, TestClass}
import org.junit.{Ignore, Test}
import scala.collection.JavaConversions._
import com.github.pushman.testini.testKits.{TestKitsProvider, TestKit}
import com.github.pushman.testini.Parameterised

class ParameterisedTestCasesProvider(testClass: TestClass, testKitsProvider: TestKitsProvider)
  extends TestCasesProvider {

  override def testCases =
    testClass.getAnnotatedMethods(classOf[Test]).map(createTestCase)

  private def createTestCase(method: FrameworkMethod) =
    if (Option(method.getMethod.getAnnotation(classOf[Ignore])).isDefined)
      IgnoredTestCase(method)
    else if (Option(method.getMethod.getAnnotation(classOf[Parameterised])).isDefined)
      ParameterisedTestCase(method, computeTestKits(method).getOrElse(noTestKitsError(method)))
    else
      NoArgTestCase(method)

  private def computeTestKits(method: FrameworkMethod) =
    testKitsProvider.provideTestKits(method)

  private def noTestKitsError(method: FrameworkMethod) =
    throw new IllegalArgumentException("Cannot find any TestKits for " + method.getMethod)
}