package com.github.pushman.testini.validation

import java.util
import scala.collection.JavaConversions._
import com.github.pushman.testini.testCases.TestCase
import com.github.pushman.testini.testKits.TestKit

class TestCaseValidator {

  def validate(testCase: TestCase): Iterable[Throwable] =
    validatePublicVoid(testCase) ++ validateKitsNotEmpty(testCase) ++ validateKitsSize(testCase)

  private def validatePublicVoid(testCase: TestCase): Seq[Throwable] = {
    val errors = new util.ArrayList[Throwable]()
    testCase.method.validatePublicVoid(false, errors)
    errors
  }

  private def validateKitsNotEmpty(testCase: TestCase): Option[Throwable] =
    if (testCase.isParameterised && testCase.kits.isEmpty && !testCase.isIgnored)
      Some(noKitsErrors(testCase))
    else
      None

  private def noKitsErrors(testCase: TestCase) =
    new NoTestKitsFoundException(testCase)

  private def validateKitsSize(testCase: TestCase): Seq[Throwable] = for {
    kit <- testCase.kits
    if (methodParametersCount(testCase) != kit.data.size)
  } yield wrongArgumentsCountError(testCase, kit)

  private def methodParametersCount(testCase: TestCase) =
    testCase.method.getMethod.getParameterTypes.length

  private def wrongArgumentsCountError(testCase: TestCase, kit: TestKit): IllegalArgumentException =
    new IllegalArgumentException("Method " + testCase.method.getMethod.getName
      + " should have " + kit.data.size + " parameters")
}