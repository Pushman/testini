package com.github.pushman.testini.xml.data

import reflect.BeanProperty
import scala.collection.JavaConversions._
import java.util.Collections
import com.github.pushman.testini.testKits.TestKit

object TestKitData {
  val Data = "kitData"
  val Ignore = "ignore"
}

case class TestKitData(@BeanProperty var kitData: java.util.List[_ <: AnyRef], @BeanProperty var ignore: Boolean)
  extends TestKit {

  def this(data: java.util.List[_ <: AnyRef]) = this(data, false)

  def data = kitData

  def this() = this(Collections.emptyList(), false)
}