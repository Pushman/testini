package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import org.junit.Ignore

case class NoArgTestCase(method: FrameworkMethod) extends TestCase {

  def kits = List.empty

  def isParameterised = false

  def isIgnored = method.getAnnotation(classOf[Ignore]) != null
}