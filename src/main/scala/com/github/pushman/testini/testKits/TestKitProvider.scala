package com.github.pushman.testini.testKits

import org.junit.runners.model.FrameworkMethod

trait TestKitProvider {

  def provideTestKits(testMethod: FrameworkMethod): Option[Seq[TestKit]]
}