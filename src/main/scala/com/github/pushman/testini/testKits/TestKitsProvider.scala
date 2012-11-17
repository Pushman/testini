package com.github.pushman.testini.testKits

import org.junit.runners.model.FrameworkMethod

trait TestKitsProvider {

  def provideTestKits(testMethod: FrameworkMethod): Option[Seq[TestKit]]
}