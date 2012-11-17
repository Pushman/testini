package com.github.pushman.testini.configuration

import com.github.pushman.testini.validation.TestCaseValidator
import com.github.pushman.testini.runner.TestRunner

trait TestRunnerValidator {
  this: TestRunner with TestSuiteContext =>

  override def validate = testCases.flatMap(testCaseValidator.validate)

  private def testCaseValidator = new TestCaseValidator
}