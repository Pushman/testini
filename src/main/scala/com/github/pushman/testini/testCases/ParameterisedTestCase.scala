package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.testKits.TestKit
import org.junit.Ignore

case class ParameterisedTestCase(method: FrameworkMethod, kits: Seq[TestKit]) extends TestCase {

  def isParameterised = true

  def isIgnored = method.getAnnotation(classOf[Ignore]) != null
}