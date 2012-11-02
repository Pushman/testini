package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.Method
import com.github.pushman.testini.data.TestKit

trait MethodExecutor {

  def execute(method: FrameworkMethod, providedMethod: Method)(implicit converter: (Any) => Seq[TestKit]): Seq[TestKit]
}
