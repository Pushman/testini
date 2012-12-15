package com.github.pushman.testini.runner

import org.junit.runners.model.{Statement, FrameworkMethod}
import org.junit.runner.Description

trait TestRunner {

  type MethodInvoker = (Any) => Statement

  def testMethods: Iterable[FrameworkMethod]

  def validate: Iterable[Throwable]

  def suiteDescription: Description

  def methodDescription(method: FrameworkMethod): Description

  def childDescription(method: RunningTestContext): Description

  def nextRunningTestContext(method: FrameworkMethod): RunningTestContext

  def methodInvoker(method: RunningTestContext): MethodInvoker
}