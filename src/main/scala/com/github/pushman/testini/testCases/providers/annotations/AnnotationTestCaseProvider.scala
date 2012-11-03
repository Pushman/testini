package com.github.pushman.testini.testCases.providers.annotations

import com.github.pushman.testini.testCases._
import org.junit.runners.model.TestClass
import com.github.pushman.testini.util.TestKitConverter

case class AnnotationTestCaseProvider(testClass: TestClass) extends TestCaseProvider {

  def testCases = TestCaseProviderImpl(testClass, testKitProviders).testCases

  val testKitProviders = List(
    MethodTestKitsProvider(ImplicitByNameMethodFinder, methodExecutor),
    MethodTestKitsProvider(InAnnotationDefinedMethodFinder, methodExecutor),
    ClassTestKitsProvider(ImplicitByPatternMethodFinder, methodExecutor)
  )

  def methodExecutor = new ByReflectionMethodExecutor with TestKitConverter
}
