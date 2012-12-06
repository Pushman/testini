package com.github.pushman.testini.testKits.providers.annotations

import com.github.pushman.testini.testKits.TestKitsProvider
import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.Method
import com.github.pushman.testini.utils.methods.{MethodExecutor, MethodFinder}
import com.github.pushman.testini.Parameterised

case class ClassTestKitsProvider(methodFinder: MethodFinder, methodExecutor: MethodExecutor)
  extends TestKitsProvider {

  def provideTestKits(testMethod: FrameworkMethod) = {
    def providerMethods = for {
      annotation <- Option(testMethod.getAnnotation(classOf[Parameterised]))
      sourceClass <- Option(annotation.source())
    } yield findProviderMethods(sourceClass)

    def findProviderMethods(clazz: Class[_]): Seq[Method] =
      methodFinder.findAllProviderMethods(testMethod.getMethod, clazz)

    def execute(providerMethods: Seq[Method]) =
      for {
        providerMethod <- providerMethods
        testKit <- methodExecutor.execute(providerMethod)
      } yield testKit

    for {
      providerMethod <- providerMethods
      testKit <- Some(execute(providerMethod))
    } yield testKit
  }
}