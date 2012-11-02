package com.github.pushman.testini.testCases

import org.junit.runners.model.FrameworkMethod
import junitparams.Parameters
import java.lang.reflect.Method

case class SourceMethodTestKitsProvider(methodFinder: TestKitProviderMethodFinder, methodExecutor: MethodExecutor)
  extends TestKitProvider {

  override def provideTestKits(method: FrameworkMethod) =
    methodFinder.findFirstProviderMethod(method.getMethod, method.getMethod.getDeclaringClass) collect {
      case providerMethod =>
        methodExecutor.execute(method, providerMethod)
    }
}

case class SourceClassTestKitsProvider(methodFinder: TestKitProviderMethodFinder, methodExecutor: MethodExecutor)
  extends TestKitProvider {

  def provideTestKits(testMethod: FrameworkMethod) =
    providerMethods(testMethod).map(execute(testMethod))

  def providerMethods(testMethod: FrameworkMethod) = for {
    annotation <- Option(testMethod.getAnnotation(classOf[Parameters]))
    sourceClass <- Option(annotation.source())
    providerMethods <- Some(findProviderMethods(testMethod, sourceClass))
  } yield providerMethods

  private def execute(testMethod: FrameworkMethod)(providerMethods: Seq[Method]) =
    providerMethods.flatMap(methodExecutor.execute(testMethod, _))

  private def findProviderMethods(testMethod: FrameworkMethod, clazz: Class[_]): Seq[Method] =
    methodFinder.findAllProviderMethods(testMethod.getMethod, clazz)
}