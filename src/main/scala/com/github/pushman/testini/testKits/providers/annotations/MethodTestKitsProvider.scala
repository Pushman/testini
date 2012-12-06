package com.github.pushman.testini.testKits.providers.annotations

import com.github.pushman.testini.testKits.TestKitsProvider
import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.utils.methods.{MethodExecutor, MethodFinder}

case class MethodTestKitsProvider(methodFinder: MethodFinder, methodExecutor: MethodExecutor)
  extends TestKitsProvider {

  override def provideTestKits(method: FrameworkMethod) =
    methodFinder.findFirstProviderMethod(method.getMethod, method.getMethod.getDeclaringClass) collect {
      case providerMethod =>
        methodExecutor.execute(providerMethod)
    }
}