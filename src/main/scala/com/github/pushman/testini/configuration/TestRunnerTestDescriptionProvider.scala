package com.github.pushman.testini.configuration

import org.junit.runners.model.FrameworkMethod
import org.junit.runner.Description
import com.github.pushman.testini.testCases.TestCase
import com.github.pushman.testini.testKits.TestKit
import com.github.pushman.testini.utils.TraversableUtils
import com.github.pushman.testini.descriptions.TestDescriptionProvider
import com.github.pushman.testini.runner.TestRunner

trait TestRunnerTestDescriptionProvider {
  this: TestRunner with TestSuiteContext with TestClassProvider =>

  override def suiteDescription = descriptionProvider.describeTest(testCases)

  override def childDescription(method: FrameworkMethod) = descriptionIterator(method).next()

  protected val descriptionIterator: Map[FrameworkMethod, Iterator[Description]] = {
    for {
      testCase <- testCases
      method = testCase.method
    } yield method -> descriptionIteratorForTestCase(testCase)
  }.toMap

  protected def descriptionIteratorForTestCase(testCase: TestCase): Iterator[Description] =
    if (testCase.isParameterised)
      TraversableUtils.iterator(testCase.kits, descriptionProvider.describeTestKit(testCase, _: TestKit))
    else
      Iterator.single(descriptionProvider.describeMethod(testCase.method))

  protected def descriptionProvider: TestDescriptionProvider = new TestDescriptionProvider(testClass)
}
