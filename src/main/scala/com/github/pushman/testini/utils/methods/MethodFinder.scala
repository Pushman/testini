package com.github.pushman.testini.utils.methods

import java.lang.reflect.Method

trait MethodFinder {

  def findFirstProviderMethod(testMethod: Method, clazz: Class[_]): Option[Method] =
    clazz.getMethods.find(matchesNamePattern(testMethod))

  def findAllProviderMethods(testMethod: Method, clazz: Class[_]): Seq[Method] =
    clazz.getMethods.filter(matchesNamePattern(testMethod))

  protected def matchesNamePattern(testMethod: Method)(providerMethod: Method): Boolean
}