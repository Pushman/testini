package com.github.pushman.testini.runner

import com.github.pushman.testini.testCases.TestCase
import com.github.pushman.testini.testKits.TestKit

sealed trait RunningTestContext {

  def testCase: TestCase
}

case class NoArgRunningTestContext(testCase: TestCase)
  extends RunningTestContext {
}

case class ParameterisedRunningTestContext(testCase: TestCase, testKit: TestKit)
  extends RunningTestContext {
}