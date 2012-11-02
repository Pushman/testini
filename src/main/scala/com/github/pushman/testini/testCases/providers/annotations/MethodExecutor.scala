package com.github.pushman.testini.testCases.providers.annotations

import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.{Modifier, Method}
import com.github.pushman.testini.data.TestKit

trait MethodExecutor {

  def execute(method: FrameworkMethod, providedMethod: Method): Seq[TestKit]
}

abstract class ByReflectionMethodExecutor extends MethodExecutor {

  def convert(invocationResult: Any): Seq[TestKit]

  override def execute(method: FrameworkMethod, providerMethod: Method) =
    convert(executionResult(method, providerMethod))

  def executionResult(method: FrameworkMethod, providerMethod: Method): Any =
    if (providerMethod.getParameterTypes.length != 0)
      throw new IllegalArgumentException("TestKit providing method: " + providerMethod.getName + " must have no parameters")
    else
      providerMethod.invoke(providerClassInstance(providerMethod))

  def providerClassInstance(providedMethod: Method): Any =
    if (Modifier.isStatic(providedMethod.getModifiers))
      null
    else
      providedMethod.getDeclaringClass.newInstance()
}

