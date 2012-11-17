package com.github.pushman.testini.utils

import com.github.pushman.testini.testKits.{TestKit, TestKitConverterError, TestKitImpl}
import scala.collection.JavaConversions._

trait TestKitConverter {

  def convert(invocationResult: Any): Seq[TestKit] =
    convertFrom(invocationResult) getOrElse testConverterError(invocationResult)

  def convertFrom(invocationResult: Any): Option[Seq[TestKit]] =
    Option(invocationResult) collect {
      case seq: Seq[TestKit] => seq
      case seq: java.util.List[TestKit] => seq
      case seq: Array[_] => convertArray(seq)
    }

  def convertArray(array: Array[_]) = for {
    arrayElement <- array
    testKit = convertSingle(arrayElement) getOrElse testConverterError(arrayElement)
  } yield testKit

  def convertSingle(obj: Any) =
    Option(obj) collect {
      case testKit: TestKit => testKit
      case kitData: Array[AnyRef] => TestKitImpl(kitData)
    }

  private def testConverterError(invocationResult: Any) =
    throw new TestKitConverterError(invocationResult)
}