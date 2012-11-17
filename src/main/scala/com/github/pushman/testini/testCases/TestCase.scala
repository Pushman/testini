package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.testKits.TestKit

trait TestCase {
  def method: FrameworkMethod

  def kits: Seq[TestKit]

  def isParameterised: Boolean
}