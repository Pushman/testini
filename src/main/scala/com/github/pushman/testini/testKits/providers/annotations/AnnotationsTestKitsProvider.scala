package com.github.pushman.testini.testKits.providers.annotations

import com.github.pushman.testini.testKits.TestKitsProvider
import finders.{ImplicitByPatternMethodFinder, InAnnotationDefinedMethodFinder, ImplicitByNameMethodFinder}
import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.utils.TestKitConverter
import com.github.pushman.testini.utils.methods.UniqueByReflectionMethodExecutor

import com.github.pushman.testini.utils.TraversableUtils._

object AnnotationsTestKitsProvider {

  def apply() =
    new AnnotationsTestKitsProvider(testKitProviders)

  val testKitProviders = List(
    new DefaultClassTestKitsProvider(ImplicitByNameMethodFinder, methodExecutor),
    new UserDefinedClassTestKitsProvider(ImplicitByPatternMethodFinder, methodExecutor),
    new DefaultClassTestKitsProvider(InAnnotationDefinedMethodFinder, methodExecutor)
  )

  def methodExecutor = new UniqueByReflectionMethodExecutor with TestKitConverter
}

case class AnnotationsTestKitsProvider(testKitsProviders: Seq[TestKitsProvider])
  extends TestKitsProvider {

  def provideTestKits(testMethod: FrameworkMethod) =
    testKitsProviders.findSome(_.provideTestKits(testMethod))
}