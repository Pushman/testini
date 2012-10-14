package com.github.pushman.testini.xml

import reflect.BeanProperty
import scala.collection.JavaConversions.setAsJavaSet
import com.github.pushman.testini.xml.data.TestKitData

class XMLTestFixture {

  @BeanProperty
  var dataSets: java.util.Set[TestKitData] = setAsJavaSet(Set())
}
