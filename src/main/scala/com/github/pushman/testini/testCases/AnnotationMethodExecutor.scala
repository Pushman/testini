package com.github.pushman.testini.testCases

import java.lang.reflect.{Modifier, Method}
import org.junit.runners.model.FrameworkMethod

object AnnotationMethodExecutor extends MethodExecutor {

  override def execute(method: FrameworkMethod, providedMethod: Method): Any =
    providedMethod.invoke(getProviderClassInstance(providedMethod))

  def getProviderClassInstance(providedMethod: Method): Any =
    if (Modifier.isStatic(providedMethod.getModifiers))
      null
    else
      providedMethod.getDeclaringClass.newInstance()
}
