package com.github.pushman.testini.data

import org.junit.runners.model.FrameworkMethod

trait TestCase {
  def method: FrameworkMethod

  def kits: Seq[TestKit]

  def isParameterised: Boolean
}

case class NoArgTestCase(method: FrameworkMethod) extends TestCase {
  def kits = List.empty

  def isParameterised = false
}

case class ParameterisedTestCase(method: FrameworkMethod, kits: Seq[TestKit]) extends TestCase {
  def isParameterised = true
}