package com.github.pushman.testini.xml.data

import reflect.BeanProperty
import java.util.Collections
import scala.collection.JavaConversions._
import com.github.pushman.testini.testCases.TestCase

object XmlTestCase {
  val TestKits = "testKits"
}

case class XmlTestCase(@BeanProperty var testKits: java.util.List[XmlTestKit]) extends TestCase {

  def this() = this(Collections.emptyList())

  override def kits = testKits

  def method = null

  def isParameterised = true

  def isIgnored = false
}