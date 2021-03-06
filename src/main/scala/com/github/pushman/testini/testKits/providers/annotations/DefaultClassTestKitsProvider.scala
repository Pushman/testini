package com.github.pushman.testini.testKits.providers.annotations

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.Parameterised
import com.github.pushman.testini.utils.methods.{MethodExecutor, MethodFinder}

class DefaultClassTestKitsProvider(methodFinder: MethodFinder, methodExecutor: MethodExecutor)
  extends ClassTestKitsProvider(methodFinder, methodExecutor) {

  override def sourceClass(testMethod: FrameworkMethod) =
    Some(testMethod.getMethod.getDeclaringClass)
}