package com.github.pushman.testini.xml.data

import reflect.BeanProperty
import scala.collection.JavaConversions._
import java.util.Collections
import com.github.pushman.testini.testKits.TestKit

object XmlTestKit {
  val Data = "kitData"
  val IsIgnored = "isIgnored"
}

case class XmlTestKit(@BeanProperty var kitData: java.util.List[_ <: AnyRef], @BeanProperty var isIgnored: Boolean)
  extends TestKit {

  def this(data: java.util.List[_ <: AnyRef]) = this(data, false)

  def data = kitData

  def this() = this(Collections.emptyList(), false)
}