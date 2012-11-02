package com.github.pushman.testini.testCases.providers.annotations

import com.github.pushman.testini.testCases._
import org.junit.runners.model.TestClass
import com.github.pushman.testini.util.TestKitConverter

object DefaultAnnotationTestCaseProvider extends TestCaseProvider {

  def testCases(testClass: TestClass) = configuration.testCases(testClass)

  val configuration = TestCaseProviderImpl(
    MethodTestKitsProvider(ImplicitByNameMethodFinder, methodExecutor),
    MethodTestKitsProvider(InAnnotationDefinedMethodFinder, methodExecutor),
    ClassTestKitsProvider(ImplicitByPatternMethodFinder, methodExecutor)
  )

  def methodExecutor = new ByReflectionMethodExecutor with TestKitConverter
}
