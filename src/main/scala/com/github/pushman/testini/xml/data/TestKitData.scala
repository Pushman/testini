package com.github.pushman.testini.xml.data

import reflect.BeanProperty
import scala.collection.JavaConversions._
import com.github.pushman.testini.data.TestKit

object TestKitData {
  val Data = "kitData"
  val Ignore = "ignore"
}

case class TestKitData(@BeanProperty var kitData: java.util.List[_],
                       @BeanProperty var ignore: Boolean) extends TestKit {

  def this(data: java.util.List[_]) = this(data, false)

  def this() = this(List(), false)

  def data = kitData
}
