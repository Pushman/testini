package com.github.pushman.testini.testKits.providers.annotations.finders

import com.github.pushman.testini.utils.methods.MethodFinder
import java.lang.reflect.Method

object ImplicitByNameMethodFinder extends MethodFinder {

  override def matchesNamePattern(testMethod: Method)(providerMethod: Method) =
    providerMethod.getName == "parametersFor" + testMethod.getName.head.toUpper + testMethod.getName.tail
}