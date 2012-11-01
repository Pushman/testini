package com.github.pushman.testini.xml.data

import reflect.BeanProperty
import scala.collection.JavaConversions._
import com.github.pushman.testini.data.TestKit

object TestKitData {
  val Data = "kitData"
  val Ignore = "ignore"
}

case class TestKitData(var data: Seq[AnyRef], @BeanProperty var ignore: Boolean) extends TestKit {

  def this(data: java.util.List[_ <: AnyRef]) = this(data, false)

  @BeanProperty var kitData: java.util.List[_ <: AnyRef] = data

  def this() = this(List.empty, false)
}
