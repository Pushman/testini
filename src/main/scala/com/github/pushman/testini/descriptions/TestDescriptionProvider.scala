package com.github.pushman.testini.descriptions

import org.junit.runner.Description
import org.junit.runners.model.{FrameworkMethod, TestClass}

import com.github.pushman.testini.util.ExtendedDescription._
import com.github.pushman.testini.data.{TestKit, TestCase}


class TestDescriptionProvider(testClass: TestClass) {

  def describeTest(testCases: Seq[TestCase]): Description =
    describeSuite ++> describeTestCases(testCases)

  private def describeSuite: Description =
    Description.createSuiteDescription(testClass.getName, testClass.getAnnotations: _*)

  private def describeTestCases(testCases: Seq[TestCase]): Iterable[Description] =
    testCases.map(describeCase)

  private def describeCase(testCase: TestCase): Description =
    describeMethod(testCase.method) ++> describeTestKits(testCase.kits)

  def describeMethod(method: FrameworkMethod): Description =
    Description.createSuiteDescription(method.getName)

  private def describeTestKits(testKits: Seq[TestKit]): Iterable[Description] =
    testKits.map(describeTestKit)

  def describeTestKit(kit: TestKit): Description =
    Description.createTestDescription(testClass.getJavaClass, kit.data.toString())
}
