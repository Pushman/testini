package com.github.pushman.testini.methods

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.data.TestKit

class ParameterisedMethod(val testMethod: FrameworkMethod, val kit: TestKit) {

  def run(testTarget: Any) {
    if (hasParameters) {
    } else {
      testMethod.invokeExplosively(testTarget)
    }
  }

  def hasParameters =
    testMethod.getMethod.getParameterTypes.length > 0
}
