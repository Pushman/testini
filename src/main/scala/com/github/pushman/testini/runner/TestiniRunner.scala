package com.github.pushman.testini.runner

import org.junit.runners.model.TestClass
import com.github.pushman.testini.XmlParametersConfiguration
import com.github.pushman.testini.configuration.providers.spring.SpringTestRunnerConfiguration
import com.github.pushman.testini.configuration.providers.annotations.AnnotationsTestRunnerConfiguration
import com.github.pushman.testini.runner.GenericTestiniTestRunner.TestRunnerBuilder

class TestiniRunner(clazz: Class[_]) extends GenericTestiniTestRunner(clazz, RunnerFromAnnotationDetector) {
}

object RunnerFromAnnotationDetector extends TestRunnerBuilder {

  def apply(testClass: TestClass): TestRunner =
    findBuilder(testClass)(testClass)

  protected def findBuilder(testClass: TestClass) =
    runnersForAnnotations.collectFirst({
      case (annotationType, builder) if testClass.getJavaClass.isAnnotationPresent(annotationType) =>
        builder
    }).getOrElse(defaultBuilder)

  protected val runnersForAnnotations = Map(
    classOf[XmlParametersConfiguration] -> SpringTestRunnerConfiguration
  )

  protected val defaultBuilder: TestRunnerBuilder =
    AnnotationsTestRunnerConfiguration
}