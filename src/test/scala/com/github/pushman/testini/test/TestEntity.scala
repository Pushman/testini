package com.github.pushman.testini.test

import reflect.BeanProperty
import scala.collection.JavaConversions._
import org.junit.Ignore

@Ignore
case class TestEntity(@BeanProperty var name: String,
                      @BeanProperty var itemsAsSet: java.util.Set[String],
                      @BeanProperty var itemsAsList: java.util.List[String]) {

  def this() = this(null, setAsJavaSet(Set()), List())
}
