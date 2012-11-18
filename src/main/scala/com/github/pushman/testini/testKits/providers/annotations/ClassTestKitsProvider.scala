package com.github.pushman.testini.testKits.providers.annotations

import com.github.pushman.testini.testKits.TestKitsProvider
import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.Method
import com.github.pushman.testini.utils.methods.{MethodExecutor, MethodFinder}
import com.github.pushman.testini.Parameterised

case class ClassTestKitsProvider(methodFinder: MethodFinder, methodExecutor: MethodExecutor)
  extends TestKitsProvider {

  def provideTestKits(testMethod: FrameworkMethod) =
    providerMethods(testMethod).map(execute(testMethod))

  def providerMethods(testMethod: FrameworkMethod) = for {
    annotation <- Option(testMethod.getAnnotation(classOf[Parameterised]))
    sourceClass <- Option(annotation.source())
    providerMethods <- Some(findProviderMethods(testMethod, sourceClass))
  } yield providerMethods

  private def execute(testMethod: FrameworkMethod)(providerMethods: Seq[Method]) =
    providerMethods.flatMap(methodExecutor.execute(testMethod, _))

  private def findProviderMethods(testMethod: FrameworkMethod, clazz: Class[_]): Seq[Method] =
    methodFinder.findAllProviderMethods(testMethod.getMethod, clazz)
}