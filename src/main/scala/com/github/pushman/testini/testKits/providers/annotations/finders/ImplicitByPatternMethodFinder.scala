package com.github.pushman.testini.testKits.providers.annotations.finders

import com.github.pushman.testini.utils.methods.MethodFinder
import java.lang.reflect.Method

object ImplicitByPatternMethodFinder extends MethodFinder {

  override def matchesNamePattern(testMethod: Method)(providerMethod: Method) =
    providerMethod.getName.startsWith("parameters") || providerMethod.getName.endsWith("Parameters")
}