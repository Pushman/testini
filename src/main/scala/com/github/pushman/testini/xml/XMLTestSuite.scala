package com.github.pushman.testini.xml

import com.google.common.collect.Sets
import data.TestCaseData
import reflect.BeanProperty

class XMLTestSuite {

  @BeanProperty
  var cases: java.util.Set[TestCaseData] = Sets.newHashSet()
}
