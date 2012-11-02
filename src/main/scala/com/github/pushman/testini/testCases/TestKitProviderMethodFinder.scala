package com.github.pushman.testini.testCases

import java.lang.reflect.Method
import junitparams.Parameters

trait TestKitProviderMethodFinder {

  def findProviderMethod(method: Method): Option[Method] =
    method.getDeclaringClass.getMethods.find(_.getName == providerMethodName(method))

  protected def providerMethodName(method: Method): String
}

object ImplicitByNameMethodFinder extends TestKitProviderMethodFinder {

  override def providerMethodName(method: Method) =
    "parametersFor" + method.getName.head.toUpper + method.getName.tail
}

object InAnnotationDefinedMethodFinder extends TestKitProviderMethodFinder {

  override def providerMethodName(method: Method) =
    method.getAnnotation(classOf[Parameters]).method()
}
