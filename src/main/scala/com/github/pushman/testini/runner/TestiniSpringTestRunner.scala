package com.github.pushman.testini.runner

import org.junit.runners.model.TestClass
import com.github.pushman.testini.testCases.providers.spring.SpringTestRunnerConfiguration

class TestiniSpringTestRunner(clazz: Class[_])
  extends TestiniTestRunner(clazz: Class[_], new SpringTestRunnerConfiguration(_: TestClass)) {
}