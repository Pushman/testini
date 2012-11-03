package com.github.pushman.testini.testCases.providers.spring

import com.github.pushman.testini.testCases.ParameterisedTestCaseProvider
import org.junit.runners.model.{FrameworkMethod, TestClass}
import java.lang.String
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import com.github.pushman.testini.data.TestCase

case class SpringTestKitsProvider(testClass: TestClass) extends ParameterisedTestCaseProvider {

  val configLocation: String = "classpath:/" + testClass.getName.replace(".", "/") + ".xml"
  val context: ApplicationContext = new ClassPathXmlApplicationContext(configLocation)

  def computeTestKits(method: FrameworkMethod) =
    Option(testCaseForMethod(method)).map(_.kits)

  def testCaseForMethod(method: FrameworkMethod): TestCase =
    context.getBean(method.getMethod.getName, classOf[TestCase])
}