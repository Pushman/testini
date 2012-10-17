package statements

import org.junit.runners.model.{FrameworkMethod, Statement}
import com.github.pushman.testini.data.TestKit

class ParameterisedStatement(val testMethod: FrameworkMethod, val kit: TestKit, testTarget: Any) extends Statement {

  def evaluate() {
    testMethod.invokeExplosively(testTarget, kit.data: _*)
  }
}
