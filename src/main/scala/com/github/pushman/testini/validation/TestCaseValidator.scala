package com.github.pushman.testini.validation

import java.util
import scala.collection.JavaConversions._
import java.lang.reflect.Method
import com.github.pushman.testini.data.{TestKit, TestCase}

class TestCaseValidator {

  def validateAll(testCases: Seq[TestCase]) =
    testCases.flatMap(validate)

  def validate(testCase: TestCase): Iterable[Throwable] =
    validatePublicVoid(testCase) ++ validateKitsNotEmpty(testCase) ++ validateKitsSize(testCase)

  private def validatePublicVoid(testCase: TestCase): Seq[Throwable] = {
    val errors = new util.ArrayList[Throwable]()
    testCase.method.validatePublicVoid(false, errors)
    errors
  }

  private def validateKitsNotEmpty(testCase: TestCase): Seq[Throwable] = {
    implicit val method = testCase.method.getMethod
    if (method.getParameterTypes.length > 0 && testCase.kits.isEmpty)
      List(noKitsErrors(method))
    else
      List.empty
  }

  private def noKitsErrors(method: Method): IllegalArgumentException =
    new IllegalArgumentException("No test kits found for parameterized method " + method.getName)

  private def validateKitsSize(testCase: TestCase): Seq[Throwable] =
    testCase.kits.flatMap(validateKitSize(testCase))

  private def validateKitSize(testCase: TestCase)(kit: TestKit): Seq[Throwable] = {
    val method = testCase.method.getMethod
    if (method.getParameterTypes.length != kit.data.size)
      List(wrongArgumentsCountError(method, kit))
    else
      List.empty
  }

  private def wrongArgumentsCountError(method: Method, kit: TestKit): IllegalArgumentException =
    new IllegalArgumentException("Method " + method.getName + " should have " + kit.data.size + " parameters")
}
