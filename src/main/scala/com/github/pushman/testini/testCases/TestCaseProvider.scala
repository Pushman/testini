package com.github.pushman.testini.testCases

import org.junit.runners.model.TestClass
import com.github.pushman.testini.xml.data.TestKitData
import org.junit.Test
import com.github.pushman.testini.data.TestCase

class TestCaseProvider(testClass: TestClass) {
  private val testMethods = testClass.getAnnotatedMethods(classOf[Test])

  def testCases: Seq[TestCase] = {
    List(new TestCase {
      def kits = List()

      def method = testMethods.get(0)

      def isParameterised = false
    }, new TestCase {
      def kits = List(new TestKitData(List("foo", "FOO")), new TestKitData(List("asdf", "ASDF")))

      def method = testMethods.get(1)

      def isParameterised = true
    })
  }

  val methods = testCases map (t => t.method)
}
