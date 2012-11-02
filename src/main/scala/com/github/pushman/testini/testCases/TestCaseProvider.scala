package com.github.pushman.testini.testCases

import com.github.pushman.testini.data.TestCase
import org.junit.runners.model.TestClass

trait TestCaseProvider {
  def testCases(testClass: TestClass): Seq[TestCase]
}