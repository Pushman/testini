package com.github.pushman.testini.configuration

import org.junit.runners.model.TestClass

trait TestClassProvider {

  def testClass: TestClass
}