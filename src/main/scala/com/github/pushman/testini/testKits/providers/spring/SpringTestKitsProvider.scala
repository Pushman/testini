package com.github.pushman.testini.testKits.providers.spring

import com.github.pushman.testini.testKits.TestKitsProvider
import org.junit.runners.model.{TestClass, FrameworkMethod}
import com.github.pushman.testini.testCases.TestCase
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext
import com.github.pushman.testini.XmlParametersConfiguration
import com.github.pushman.testini.utils.StringOption

class SpringTestKitsProvider(testClass: TestClass) extends TestKitsProvider {

  def provideTestKits(testMethod: FrameworkMethod) =
    for {
      testCase <- Option(testCaseForMethod(testMethod))
      kit <- Some(testCase.kits)
    } yield testCase.kits

  private def testCaseForMethod(method: FrameworkMethod) =
    context.getBean(method.getMethod.getName, classOf[TestCase])

  private lazy val context: ApplicationContext =
    new ClassPathXmlApplicationContext(configLocation)

  private lazy val configLocation =
    StringOption(configLocationFromAnnotation).getOrElse(defaultConfigLocation)

  private lazy val configLocationFromAnnotation =
    testClass.getJavaClass.getAnnotation(classOf[XmlParametersConfiguration]).source()

  private lazy val defaultConfigLocation =
    "classpath:/" + testClass.getName.replace(".", "/") + ".xml"
}