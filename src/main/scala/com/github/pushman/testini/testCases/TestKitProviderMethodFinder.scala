package com.github.pushman.testini.testCases

import java.lang.reflect.Method

trait TestKitProviderMethodFinder {

  def findProviderMethod(method: Method): Option[Method]
}

object ImplicitByNameMethodFinder extends TestKitProviderMethodFinder {

  def findProviderMethod(method: Method) =
    method.getDeclaringClass.getMethods.find(_.getName == dataSourceMethodName(method))

  private def dataSourceMethodName(method: Method) =
    "parametersFor" + method.getName.head.toUpper + method.getName.tail
}