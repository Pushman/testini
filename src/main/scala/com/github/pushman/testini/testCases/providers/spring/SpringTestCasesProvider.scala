package com.github.pushman.testini.testCases.providers.spring

import com.github.pushman.testini.testCases.{TestCase, ParameterisedTestCasesProvider}
import org.junit.runners.model.{FrameworkMethod, TestClass}
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import com.github.pushman.testini.XmlParametersConfiguration

case class SpringTestCasesProvider(testClass: TestClass) extends ParameterisedTestCasesProvider(testClass) {

  override def computeTestKits(method: FrameworkMethod) =
    testCaseForMethod(method) collect {
      case testCase => testCase.kits
    }

  private def testCaseForMethod(method: FrameworkMethod) =
    Option(context.getBean(method.getMethod.getName, classOf[TestCase]))

  private lazy val context: ApplicationContext = new ClassPathXmlApplicationContext(configLocation)

  private lazy val configLocation = configLocationFromAnnotation.getOrElse(defaultConfigLocation)

  private lazy val configLocationFromAnnotation = {
    val source = testClass.getJavaClass.getAnnotation(classOf[XmlParametersConfiguration]).source()
    if (source != "")
      Some(source)
    else
      None
  }

  private lazy val defaultConfigLocation = "classpath:/" + testClass.getName.replace(".", "/") + ".xml"
}