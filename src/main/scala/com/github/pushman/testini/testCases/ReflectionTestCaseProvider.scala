package com.github.pushman.testini.testCases

import com.github.pushman.testini.data.{TestKit, NoArgTestCase, ParameterisedTestCase}
import org.junit.runners.model.{TestClass, FrameworkMethod}
import org.junit.Test
import scala.collection.JavaConversions._
import java.lang.reflect.Method
import com.github.pushman.testini.exceptions.TestCaseProviderMethodResultTypeException

class ReflectionTestCaseProvider(testClass: TestClass) extends TestCaseProvider {

  override def testCases =
    testClass.getAnnotatedMethods(classOf[Test]).map(createTestCase)

  def createTestCase(method: FrameworkMethod) =
    if (method.getMethod.getParameterTypes.length > 0)
      ParameterisedTestCase(method, getKits(method))
    else
      NoArgTestCase(method)

  def getKits(method: FrameworkMethod): Seq[TestKit] = {
    val providerMethod = findProviderMethod(method)
    val providerClassInstance = providerMethod.getDeclaringClass.newInstance()
    providerMethod.invoke(providerClassInstance) match {
      case seq: Seq[TestKit] => seq
      case seq: java.util.List[TestKit] => seq
      case _ => throw new TestCaseProviderMethodResultTypeException(providerMethod,
        List(classOf[Seq[TestKit]], classOf[java.util.List[TestKit]]))
    }
  }

  private def findProviderMethod(method: FrameworkMethod): Method =
    testClass.getJavaClass.getMethod(dataSourceMethodName(method))

  private def dataSourceMethodName(method: FrameworkMethod) =
    "parametersFor" + method.getName.head.toUpper + method.getName.tail
}
