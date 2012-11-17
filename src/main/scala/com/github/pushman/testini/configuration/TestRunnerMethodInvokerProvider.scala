package com.github.pushman.testini.configuration

import org.junit.internal.runners.statements.InvokeMethod
import com.github.pushman.testini.statements.ParameterisedStatement
import com.github.pushman.testini.runner.{ParameterisedRunningTestContext, NoArgRunningTestContext, RunningTestContext, TestRunner}

trait TestRunnerMethodInvokerProvider {
  this: TestRunner with TestSuiteContext =>

  override def methodInvoker(context: RunningTestContext) =
    context match {
      case NoArgRunningTestContext(testCase) =>
        new InvokeMethod(testCase.method, _: Any)
      case ParameterisedRunningTestContext(testCase, testKit) =>
        new ParameterisedStatement(testCase.method, testKit, _: Any)
    }
}