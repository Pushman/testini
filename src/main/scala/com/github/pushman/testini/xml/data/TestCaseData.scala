package com.github.pushman.testini.xml.data

import reflect.BeanProperty
import java.util.Collections
import scala.collection.JavaConversions._
import com.github.pushman.testini.data.TestCase

object TestCaseData {
  val TestKits = "testKits"
}

case class TestCaseData(@BeanProperty var testKits: java.util.List[TestKitData]) extends TestCase {

  def this() = this(Collections.emptyList())

  override def kits = testKits

  def method = null

  def isParameterised = true
}
