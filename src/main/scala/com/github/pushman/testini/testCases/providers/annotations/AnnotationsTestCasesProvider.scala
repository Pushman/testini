package com.github.pushman.testini.testCases.providers.annotations

import com.github.pushman.testini.testCases._
import com.github.pushman.testini.utils.TestKitConverter
import org.junit.runners.model.{FrameworkMethod, TestClass}
import com.github.pushman.testini.testKits.{TestKit, TestKitsProvider}
import com.github.pushman.testini.utils.TraversableUtils._
import com.github.pushman.testini.testKits.providers.annotations.{ClassTestKitsProvider, MethodTestKitsProvider}
import com.github.pushman.testini.utils.methods.UniqueByReflectionMethodExecutor
import com.github.pushman.testini.testKits.providers.annotations.finders.{InAnnotationDefinedMethodFinder, ImplicitByPatternMethodFinder, ImplicitByNameMethodFinder}

object AnnotationsTestCasesProvider {

  def apply(testClass: TestClass) =
    new AnnotationsTestCasesProvider(testClass, testKitProviders)

  val testKitProviders = List(
    MethodTestKitsProvider(ImplicitByNameMethodFinder, methodExecutor),
    MethodTestKitsProvider(InAnnotationDefinedMethodFinder, methodExecutor),
    ClassTestKitsProvider(ImplicitByPatternMethodFinder, methodExecutor)
  )

  def methodExecutor = new UniqueByReflectionMethodExecutor with TestKitConverter
}

case class AnnotationsTestCasesProvider(testClass: TestClass, testKitsProviders: Seq[TestKitsProvider])
  extends ParameterisedTestCasesProvider(testClass) {

  override def computeTestKits(method: FrameworkMethod): Option[Seq[TestKit]] =
    testKitsProviders.findSome(_.provideTestKits(method))
}