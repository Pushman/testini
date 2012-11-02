package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.Method
import com.github.pushman.testini.data.TestKit

abstract class MethodExecutor {

  def execute(method: FrameworkMethod, providedMethod: Method): Seq[TestKit]
}
