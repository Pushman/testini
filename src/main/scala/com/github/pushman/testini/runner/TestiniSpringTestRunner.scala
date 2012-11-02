package com.github.pushman.testini.runner

import org.junit.runners.model.TestClass
import com.github.pushman.testini.testCases.providers.spring.TestRunnerSpringConfiguration

class TestiniSpringTestRunner(clazz: Class[_])
  extends TestiniTestRunner(clazz: Class[_], new TestRunnerSpringConfiguration(_: TestClass)) {
}