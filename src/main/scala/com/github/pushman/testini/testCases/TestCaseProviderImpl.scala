package com.github.pushman.testini.testCases

import com.github.pushman.testini.data.{TestKit, NoArgTestCase, ParameterisedTestCase}
import org.junit.runners.model.{TestClass, FrameworkMethod}
import org.junit.Test
import scala.collection.JavaConversions._
import com.github.pushman.testini.util.TraversableUtils._
import com.github.pushman.testini.util.TestKitConverter

object DefaultAnnotationTestCaseProvider extends TestCaseProvider {

  def testCases(testClass: TestClass) = configuration.testCases(testClass)

  val configuration = TestCaseProviderImpl(
    SourceMethodTestKitsProvider(ImplicitByNameMethodFinder, methodExecutor),
    SourceMethodTestKitsProvider(InAnnotationDefinedMethodFinder, methodExecutor),
    SourceClassTestKitsProvider(ImplicitByPatternMethodFinder, methodExecutor)
  )

  def methodExecutor = new AnnotationMethodExecutor with TestKitConverter
}

case class TestCaseProviderImpl(testKitsProviders: TestKitProvider*) extends TestCaseProvider {

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