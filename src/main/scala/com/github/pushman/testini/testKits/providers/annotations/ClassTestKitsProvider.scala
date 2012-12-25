package com.github.pushman.testini.testKits.providers.annotations

import com.github.pushman.testini.testKits.{TestKit, TestKitsProvider}
import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.Method
import com.github.pushman.testini.utils.methods.{MethodExecutor, MethodFinder}

abstract class ClassTestKitsProvider(methodFinder: MethodFinder, methodExecutor: MethodExecutor)
  extends TestKitsProvider {

  def provideTestKits(testMethod: FrameworkMethod): Option[Seq[TestKit]] = {
    for {
      sourceClass <- sourceClass(testMethod)
      providerMethods <- Some(methodFinder.findProviderMethods(testMethod.getMethod, sourceClass))
      providerMethod <- Some(providerMethods) if !providerMethods.isEmpty
      testKit <- Some(execute(providerMethod))
    } yield testKit
  }

  protected def sourceClass(testMethod: FrameworkMethod): Option[Class[_]]

  private def execute(providerMethods: Seq[Method]) =
    providerMethods.flatMap(methodExecutor.execute)
}