package com.github.pushman.testini.testCases

import java.lang.reflect.{Modifier, Method}
import org.junit.runners.model.FrameworkMethod
import com.github.pushman.testini.data.TestKit
import com.github.pushman.testini.util.TestKitConverter

object AnnotationMethodExecutor extends MethodExecutor {

  def converter: (Any) => Seq[TestKit] =
    TestKitConverter.forceConvertFrom(_)

  override def execute(method: FrameworkMethod, providerMethod: Method)(implicit converter: (Any) => Seq[TestKit]) =
    providerMethod.invoke(providerClassInstance(providerMethod))

  def providerClassInstance(providedMethod: Method): Any =
    if (Modifier.isStatic(providedMethod.getModifiers))
      null
    else
      providedMethod.getDeclaringClass.newInstance()
}