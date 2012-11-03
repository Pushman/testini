package com.github.pushman.testini.data

import org.junit.runners.model.FrameworkMethod

trait TestCase {
  def method: FrameworkMethod

  def kits: Seq[TestKit]

  def isParameterised: Boolean
}