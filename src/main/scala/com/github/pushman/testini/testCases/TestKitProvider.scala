package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.data.TestKit

trait TestKitProvider {

  def provideTestKits(testMethod: FrameworkMethod): Option[Seq[TestKit]]
}