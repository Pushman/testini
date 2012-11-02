package com.github.pushman.testini.testCases

import java.lang.reflect.Method
import junitparams.Parameters
import scala.Option
import scala.Predef._

trait TestKitProviderMethodFinder {

  def findFirstProviderMethod(testMethod: Method, clazz: Class[_]): Option[Method] =
    clazz.getMethods.find(matchesNamePattern(testMethod))

  def findAllProviderMethods(testMethod: Method, clazz: Class[_]): Seq[Method] =
    clazz.getMethods.filter(matchesNamePattern(testMethod))

  protected def matchesNamePattern(testMethod: Method)(providerMethod: Method): Boolean
}

object ImplicitByNameMethodFinder extends TestKitProviderMethodFinder {

  override def matchesNamePattern(testMethod: Method)(providerMethod: Method) =
    providerMethod.getName == "parametersFor" + testMethod.getName.head.toUpper + testMethod.getName.tail
}

object ImplicitByPatternMethodFinder extends TestKitProviderMethodFinder {

  override def matchesNamePattern(testMethod: Method)(providerMethod: Method) =
    providerMethod.getName.startsWith("parameters") || providerMethod.getName.endsWith("Parameters")
}

object InAnnotationDefinedMethodFinder extends TestKitProviderMethodFinder {
  override def matchesNamePattern(testMethod: Method)(providerMethod: Method) = {
    for {
      annotation <- Option(testMethod.getAnnotation(classOf[Parameters]))
      providerMethodName <- Some(annotation.method())
    } yield providerMethod.getName == providerMethodName
  }.getOrElse(false)
}