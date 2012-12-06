package com.github.pushman.testini.utils.methods

import java.lang.reflect.{Modifier, Method}
import com.github.pushman.testini.testKits.TestKit

trait MethodExecutor {

  def execute(providerMethod: Method): Seq[TestKit]
}

abstract class ByReflectionMethodExecutor extends MethodExecutor {

  def convert(invocationResult: Any): Seq[TestKit]

  override def execute(providerMethod: Method) =
    convert(executionResult(providerMethod))

  def executionResult(providerMethod: Method): Any =
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

abstract class UniqueByReflectionMethodExecutor extends ByReflectionMethodExecutor {

  override def execute(providerMethod: Method) =
    super.execute(providerMethod) match {
      case Nil => Nil
      case Seq(x) => List(x)
      case xs => xs.head +: obtainUniqueResults(xs, providerMethod)
    }

  private def obtainUniqueResults(results: Seq[TestKit], providerMethod: Method) = for {
    i <- 1 until results.length
  } yield super.execute(providerMethod)(i)
}