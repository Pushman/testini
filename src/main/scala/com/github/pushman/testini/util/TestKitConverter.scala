package com.github.pushman.testini.util

import com.github.pushman.testini.data.TestKit
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

class TestKitConverterError(val invocationResult: Any)
  extends RuntimeException("Cannot convert to Seq[TestKit] from " + invocationResult +
    ", possible sources are: " + List(classOf[Seq[TestKit]], classOf[java.util.List[TestKit]], "Object[]")) {
}

case class TestKitImpl(data: Seq[AnyRef]) extends TestKit {

  def ignore = false
}