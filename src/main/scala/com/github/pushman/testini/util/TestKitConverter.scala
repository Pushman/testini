package com.github.pushman.testini.util

import com.github.pushman.testini.data.TestKit
import scala.collection.JavaConversions._

trait TestKitConverter {

  def convert(invocationResult: Any): Seq[TestKit] =
    convertFrom(invocationResult).getOrElse(testConverterError(invocationResult))

  private def testConverterError(invocationResult: Any) =
    throw new TestKitConverterError(invocationResult)

  def convertFrom(invocationResult: Any): Option[Seq[TestKit]] =
    invocationResult match {
      case seq: Seq[TestKit] => Some(seq)
      case seq: java.util.List[TestKit] => Some(seq)
      case other => None
    }
}

class TestKitConverterError(val invocationResult: Any)
  extends RuntimeException("Cannot convert to TestKit from " + invocationResult +
    ", possible sources are: " + List(classOf[Seq[TestKit]], classOf[java.util.List[TestKit]])) {
}
