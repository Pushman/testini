package com.github.pushman.testini.statements

import org.junit.runners.model.{FrameworkMethod, Statement}
import com.github.pushman.testini.testKits.TestKit

class ParameterisedStatement(val testMethod: FrameworkMethod, val kit: TestKit, testTarget: Any) extends Statement {

  def evaluate() {
    testMethod.invokeExplosively(testTarget, kit.data: _*)
  }
}