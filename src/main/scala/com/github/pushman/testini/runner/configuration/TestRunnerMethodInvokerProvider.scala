package com.github.pushman.testini.runner.configuration

import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.data.{TestKit, TestCase}
import com.github.pushman.testini.util.TraversableUtils
import org.junit.internal.runners.statements.InvokeMethod
import com.github.pushman.testini.statements.ParameterisedStatement
import com.github.pushman.testini.runner.TestRunner

trait TestRunnerMethodInvokerProvider {
  this: TestRunner with TestSuiteContext =>

  override def methodInvoker(method: FrameworkMethod) = methodInvokerIterator(method).next()

  protected val methodInvokerIterator: Map[FrameworkMethod, Iterator[MethodInvoker]] = {
    for {
      testCase <- testCases
      method = testCase.method
    } yield method -> methodInvokersForTestCase(testCase)
  }.toMap

  private def methodInvokersForTestCase(testCase: TestCase): Iterator[MethodInvoker] =
    if (testCase.isParameterised)
      TraversableUtils.iterator(testCase.kits, invokerForKit(testCase.method))
    else
      Iterator.single(new InvokeMethod(testCase.method, _: Any))

  private def invokerForKit(method: FrameworkMethod)(testKit: TestKit) =
    new ParameterisedStatement(method, testKit, _: Any)
}
