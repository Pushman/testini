package statements

import org.junit.runners.model.Statement
import com.github.pushman.testini.methods.ParameterisedMethod

class ParameterisedStatement(parameterisedMethod: ParameterisedMethod, testTarget: Any) extends Statement {

  def evaluate() {
    parameterisedMethod.run(testTarget)
  }
}
