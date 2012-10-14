package com.github.pushman.testini.methods

import com.github.pushman.testini.data.{TestKit, TestCase}
import org.junit.runners.model.FrameworkMethod

class ParameterisedMethodFactory {

  def createStatements(testCase: TestCase): Seq[ParameterisedMethod] =
    createStatementsForKits(testCase.kits)(testCase.method)

  private def createStatementsForKits(kits: Seq[TestKit])(implicit method: FrameworkMethod) =
    kits.map(createStatement)

  private def createStatement(kit: TestKit)(implicit method: FrameworkMethod) =
    new ParameterisedMethod(method, kit)
}
