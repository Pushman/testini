package com.github.pushman.testini.validation

import java.util
import scala.collection.JavaConversions._
import com.github.pushman.testini.data.{TestKit, TestCase}

class TestCaseValidator {

  def validate(testCase: TestCase): Iterable[Throwable] =
    validatePublicVoid(testCase) ++ validateKitsNotEmpty(testCase) ++ validateKitsSize(testCase)

  private def validatePublicVoid(testCase: TestCase): Seq[Throwable] = {
    val errors = new util.ArrayList[Throwable]()
    testCase.method.validatePublicVoid(false, errors)
    errors
  }

  private def validateKitsNotEmpty(testCase: TestCase): Option[Throwable] =
    if (testCase.isParameterised && testCase.kits.isEmpty)
      Some(noKitsErrors(testCase))
    else
      None

  private def noKitsErrors(testCase: TestCase): IllegalArgumentException =
    new IllegalArgumentException("No test kits found for parameterized method " + testCase.method.getMethod.getName)

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
