package com.github.pushman.testini.testCases

import com.github.pushman.testini.data.{TestKit, NoArgTestCase, ParameterisedTestCase}
import org.junit.runners.model.{TestClass, FrameworkMethod}
import org.junit.Test
import scala.collection.JavaConversions._
import com.github.pushman.testini.util.TraversableUtils._

trait DefaultAnnotationTestCaseProvider {

  def testCaseProvider: TestCaseProvider = AnnotationTestCaseProviderImpl(
    ReflectionTestKitsProvider(ImplicitByNameMethodFinder, AnnotationMethodExecutor)
  )
}

abstract class AnnotationTestCaseProvider extends TestCaseProvider {

  val testKitsProviders: Seq[TestKitProvider]

  override def testCases(testClass: TestClass) =
    testClass.getAnnotatedMethods(classOf[Test]).map(createTestCase)

  def createTestCase(method: FrameworkMethod) =
    if (method.getMethod.getParameterTypes.length > 0)
      ParameterisedTestCase(method, computeTestKits(method))
    else
      NoArgTestCase(method)

  def computeTestKits(method: FrameworkMethod): Seq[TestKit] =
    testKitsProviders.findSome(_.provideTestKits(method)).getOrElse(noTestKitProviderError(method))

  def noTestKitProviderError(method: FrameworkMethod) =
    throw new IllegalArgumentException("Cannot find TestKitProvider for " + method.getMethod)
}

case class AnnotationTestCaseProviderImpl(testKitsProviders: TestKitProvider*) extends AnnotationTestCaseProvider