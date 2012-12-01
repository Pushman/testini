package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod

case class IgnoredTestCase(method: FrameworkMethod) extends TestCase {

  def kits = List.empty

  def isParameterised = false

  def isIgnored = true
}