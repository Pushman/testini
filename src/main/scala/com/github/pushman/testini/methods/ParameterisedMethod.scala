package com.github.pushman.testini.methods

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.data.TestKit
import scala.collection.JavaConversions._

object ParameterisedMethod {
  implicit def extractFrameworkMethods(parameterisedMethods: Seq[ParameterisedMethod]): java.util.List[FrameworkMethod] = {
    parameterisedMethods.map(_.testMethod)
  }
}

class ParameterisedMethod(val testMethod: FrameworkMethod, val kit: TestKit) {

  def run(testTarget: Any) {
    if (hasParameters) {
      testMethod.invokeExplosively(testTarget, kit.data: _*)
    } else {
      testMethod.invokeExplosively(testTarget)
    }
  }

  def hasParameters =
    testMethod.getMethod.getParameterTypes.length > 0
}
