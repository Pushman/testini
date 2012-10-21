package com.github.pushman.testini.testCases

import com.github.pushman.testini.data.{TestKit, NoArgTestCase, ParameterisedTestCase}
import org.junit.runners.model.{TestClass, FrameworkMethod}
import org.junit.Test
import scala.collection.JavaConversions._
import com.github.pushman.testini.util.TraversableUtils._

class AnnotationTestCaseProvider(testClass: TestClass, testKitsProviders: Seq[TestKitProvider])
  extends TestCaseProvider {

  override def testCases =
    testClass.getAnnotatedMethods(classOf[Test]).map(createTestCase)

  def createTestCase(method: FrameworkMethod) =
    if (method.getMethod.getParameterTypes.length > 0)
      ParameterisedTestCase(method, getKits(method))
    else
      NoArgTestCase(method)

  def getKits(method: FrameworkMethod): Seq[TestKit] =
    testKitsProviders.findSome(_.provideTestKits(method)) match {
      case Some(testKits) => testKits
      case None => throw new IllegalArgumentException("Cannot find TestKitProvider for " + method)
    }
}