package com.github.pushman.testini.configuration

import com.github.pushman.testini.testCases.TestCase

trait TestCasesProvider {

  def testCases: Iterable[TestCase]
}