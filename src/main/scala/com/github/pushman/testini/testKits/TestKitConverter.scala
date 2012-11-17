package com.github.pushman.testini.testKits

import scala.collection.JavaConversions._



class TestKitConverterError(val invocationResult: Any)
  extends RuntimeException("Cannot convert to Seq[TestKit] from " + invocationResult +
    ", possible sources are: " + List(classOf[Seq[TestKit]], classOf[java.util.List[TestKit]], "Object[]")) {
}

case class TestKitImpl(data: Seq[AnyRef]) extends TestKit {

  def ignore = false
}