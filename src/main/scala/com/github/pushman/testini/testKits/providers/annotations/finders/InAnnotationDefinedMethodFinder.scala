package com.github.pushman.testini.testKits.providers.annotations.finders

import java.lang.reflect.Method
import junitparams.Parameters
import scala.Option
import scala.Predef._
import com.github.pushman.testini.utils.methods.MethodFinder

object InAnnotationDefinedMethodFinder extends MethodFinder {

  override def matchesNamePattern(testMethod: Method)(providerMethod: Method) = {
    for {
      annotation <- Option(testMethod.getAnnotation(classOf[Parameters]))
      providerMethodName <- Some(annotation.method())
    } yield providerMethod.getName == providerMethodName
  }.getOrElse(false)
}