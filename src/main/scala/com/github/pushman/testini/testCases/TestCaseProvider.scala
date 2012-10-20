package com.github.pushman.testini.testCases

import com.github.pushman.testini.data.TestCase

trait TestCaseProvider {
  def testCases: Seq[TestCase]
}
