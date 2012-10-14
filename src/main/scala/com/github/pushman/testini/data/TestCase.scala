package com.github.pushman.testini.data

import org.junit.runners.model.FrameworkMethod

trait TestCase {

  def method: FrameworkMethod

  def kits: Seq[TestKit]
}

object TestCaseHelper {

  implicit def extendTestCasesSeq(testCases: Seq[TestCase]): ExtendedTestCasesSeq = {
    new ExtendedTestCasesSeq(testCases)
  }

  class ExtendedTestCasesSeq(testCases: Seq[TestCase]) {

    def extractFrameworkMethods =
      testCases.flatMap(extractMethod)

    def extractMethod(testCase: TestCase): Seq[FrameworkMethod] = {
      if (testCase.kits.isEmpty)
        List(testCase.method)
      else
        for (i <- testCase.kits) yield testCase.method
    }

    def findMethod(method: FrameworkMethod) = {
      testCases.find(method == _.method) match {
        case Some(testCase) => testCase
        case None => throw new NoSuchElementException("TestCase with method " + method + " not found")
      }
    }
  }
}