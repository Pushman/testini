package com.github.pushman.testini.validation

import java.util
import scala.collection.JavaConversions._
import java.lang.reflect.Method
import com.github.pushman.testini.data.{TestKit, TestCase}

class TestCaseValidator {

  def validateAll(testCases: Seq[TestCase]) =
    testCases.flatMap(validate)

  def validate(testCase: TestCase): Iterable[Throwable] =
    validatePublicVoid(testCase) ++ validateKitsNotEmpty(testCase) ++ testCase.kits.flatMap(validateKitSize(testCase))

  private def validatePublicVoid(testCase: TestCase): Seq[Throwable] = {
    val errors = new util.ArrayList[Throwable]()
    testCase.method.validatePublicVoid(false, errors)
    errors
  }

  private def validateKitsNotEmpty(testCase: TestCase): Seq[Throwable] = {
    implicit val method = testCase.method.getMethod
    if (method.getParameterTypes.length > 0 && testCase.kits.isEmpty)
      List(noKitsErrors)
    else
      List.empty
  }

  private def noKitsErrors(implicit method: Method): IllegalArgumentException = {
    new IllegalArgumentException("No test kits found for parameterized method " + method.getName)
  }

  private def validateKitSize(testCase: TestCase)(kit: TestKit): Seq[Throwable] = {
    implicit val method = testCase.method.getMethod
    if (method.getParameterTypes.length != kit.data.size)
      List(wrongArgumentsCountError(kit))
    else
      List.empty
  }

  private def wrongArgumentsCountError(kit: TestKit)(implicit method: Method): IllegalArgumentException = {
    new IllegalArgumentException("Method " + method.getName + " should have " + kit.data.size + " parameters")
  }
}
