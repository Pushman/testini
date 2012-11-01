package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import java.lang.reflect.Method

trait MethodExecutor {

  def execute(method: FrameworkMethod, providedMethod: Method): Any
}
