package com.github.pushman.testini.utils.methods

import java.lang.reflect.Method

trait MethodFinder {

  def findProviderMethods(testMethod: Method, clazz: Class[_]): Seq[Method] =
    clazz.getMethods.filter(matchesNamePattern(testMethod))

  protected def matchesNamePattern(testMethod: Method)(providerMethod: Method): Boolean
}