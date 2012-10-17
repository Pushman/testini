package com.github.pushman.testini.runner

import org.junit.runners.model.{Statement, FrameworkMethod}
import com.github.pushman.testini.data.TestCase
import com.github.pushman.testini.descriptions.TestDescriptionProvider
import org.junit.runner.Description
import statements.ParameterisedStatement

case class ExecutedTestKitHolder(testCase: TestCase) {
  private val iterator = testCase.kits.iterator
  private var current = iterator.next()

  def currentTestKit = current

  def moveToNextKit() {
    current = if (iterator.hasNext) iterator.next() else null
  }
}

class TestiniParameterisedTestRunner(testCases: Seq[TestCase], descriptionProvider: TestDescriptionProvider) {

  val testKitsForMethods: Map[FrameworkMethod, ExecutedTestKitHolder] = {
    testCases.filter(_.isParameterised).map(t => (t.method, new ExecutedTestKitHolder(t))).toMap
  }

  def descriptionForTestCase(method: FrameworkMethod): Description = {
    testKitsForMethods get method match {
      case Some(testKitHolder) =>
        descriptionProvider.describeTestKit(testKitHolder.currentTestKit)
      case None =>
        descriptionProvider.describeMethod(method)
    }
  }

  def methodInvoker(method: FrameworkMethod, testTarget: Any): Option[Statement] = {
    testKitsForMethods get method match {
      case Some(executedTestCase) => {
        Some(new ParameterisedStatement(method, executedTestCase.currentTestKit, testTarget))
      }
      case None =>
        None
    }
  }

  def notifyMethodInvoked(method: FrameworkMethod) {
    testKitsForMethods get method match {
      case Some(executedTestCase) =>
        executedTestCase.moveToNextKit()
      case None => ;
    }
  }
}
