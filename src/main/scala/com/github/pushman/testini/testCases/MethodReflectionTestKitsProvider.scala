package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.data.TestKit
import java.lang.reflect.{Modifier, Method}
import com.github.pushman.testini.util.TestKitConverter

object MethodReflectionTestKitsProvider extends TestKitProvider {

  override def provideTestKits(method: FrameworkMethod): Option[Seq[TestKit]] =
    findProviderMethod(method.getMethod) match {
      case Some(providedMethod) =>
        Some(extractTestKits(executeMethod(providedMethod)))
      case None => None
    }

  def executeMethod(providedMethod: Method): Any =
    providedMethod.invoke(getProviderClassInstance(providedMethod))

  def extractTestKits(invocationResult: Any): Seq[TestKit] =
    TestKitConverter.forceConvertFrom(invocationResult)

  def getProviderClassInstance(providedMethod: Method): Any =
    if (Modifier.isStatic(providedMethod.getModifiers))
      null
    else
      providedMethod.getDeclaringClass.newInstance()

  private def findProviderMethod(method: Method): Option[Method] =
    method.getDeclaringClass.getMethods.find(_.getName == dataSourceMethodName(method))

  private def dataSourceMethodName(method: Method) =
    "parametersFor" + method.getName.head.toUpper + method.getName.tail
}