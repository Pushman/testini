package com.github.pushman.testini.xml.data

import reflect.BeanProperty
import java.util.Collections
import scala.collection.JavaConversions._
import com.github.pushman.testini.testCases.TestCase

object TestCaseData {
  val TestKits = "testKits"
  val IsIgnored = "isIgnored"
}

case class TestCaseData(@BeanProperty var testKits: java.util.List[TestKitData],
                        @BeanProperty var isIgnored: Boolean) extends TestCase {

  def this(testKits: java.util.List[TestKitData]) = this(testKits, false)

  def this() = this(Collections.emptyList())

  override def kits = testKits

  def method = null

  def isParameterised = true
}