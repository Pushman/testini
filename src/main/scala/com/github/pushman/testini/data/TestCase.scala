package com.github.pushman.testini.data

import org.junit.runners.model.FrameworkMethod

object TestCase {

  implicit def extendTestCasesSeq(testCases: Seq[TestCase]): ExtendedTestCasesSeq = {
    new ExtendedTestCasesSeq(testCases)
  }

  class ExtendedTestCasesSeq(testCases: Seq[TestCase]) {

    def extractFrameworkMethods =
      testCases.flatMap(extractMethod)

    private def extractMethod(testCase: TestCase): Seq[FrameworkMethod] = {
      if (testCase.isParameterised)
        for (i <- testCase.kits) yield testCase.method
      else
        List(testCase.method)
    }

    def findMethod(method: FrameworkMethod) = {
      testCases.find(method == _.method) match {
        case Some(testCase) => testCase
        case None => throw new NoSuchElementException("TestCase with method " + method + " not found")
      }
    }
  }
}

trait TestCase {
  def method: FrameworkMethod

  def kits: Seq[TestKit]

  def isParameterised: Boolean
}

case class NoArgTestCase(method: FrameworkMethod) extends TestCase {
  def kits = List.empty

  def isParameterised = false
}

case class ParameterisedTestCase(method: FrameworkMethod, kits: Seq[TestKit]) extends TestCase {
  def isParameterised = true
}