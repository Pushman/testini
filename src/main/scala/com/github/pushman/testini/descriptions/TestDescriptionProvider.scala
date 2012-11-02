package com.github.pushman.testini.descriptions

import org.junit.runner.Description
import org.junit.runners.model.{FrameworkMethod, TestClass}

import com.github.pushman.testini.util.ExtendedDescription._
import com.github.pushman.testini.data.{TestKit, TestCase}


class TestDescriptionProvider(testClass: TestClass) {

  def describeTest(testCases: Iterable[TestCase]): Description =
    describeSuite ++> describeTestCases(testCases)

  private def describeSuite: Description =
    Description.createSuiteDescription(testClass.getName, testClass.getAnnotations: _*)

  private def describeTestCases(testCases: Iterable[TestCase]): Iterable[Description] =
    testCases.map(describeCase)

  private def describeCase(testCase: TestCase): Description =
    describeMethod(testCase.method) ++> describeTestKits(testCase)

  def describeMethod(method: FrameworkMethod): Description =
    Description.createSuiteDescription(method.getName)

  private def describeTestKits(testCase: TestCase): Iterable[Description] =
    testCase.kits.map(describeTestKit(testCase, _))

  def describeTestKit(testCase: TestCase, kit: TestKit): Description =
    Description.createTestDescription(testClass.getJavaClass, testKitSummary(testCase, kit))

  def testKitSummary(testCase: TestCase, kit: TestKit): String =
    kit.data.mkString(", ") + " - " + testCase.method.getName
}
