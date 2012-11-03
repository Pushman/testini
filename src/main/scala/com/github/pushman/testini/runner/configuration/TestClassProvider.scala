package com.github.pushman.testini.runner.configuration

import org.junit.runners.model.TestClass

trait TestClassProvider {

  def testClass: TestClass
}