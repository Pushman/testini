package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod

case class NoArgTestCase(method: FrameworkMethod) extends TestCase {

  def kits = List.empty

  def isParameterised = false
}