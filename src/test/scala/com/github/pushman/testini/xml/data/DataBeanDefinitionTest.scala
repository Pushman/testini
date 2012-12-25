package com.github.pushman.testini.xml.data

import org.junit.Test
import org.junit.Assert._
import org.springframework.context.support.ClassPathXmlApplicationContext
import java.util.{Collections, Locale}
import scala.collection.JavaConversions._

class DataBeanDefinitionTest {

  @Test
  def shouldCreateSimpleEntity() {
    // given
    val expectedEntity = simpleTestKit

    // when
    val entity = loadEntityFromXml("simpleKit", classOf[XmlTestKit])

    // then
    assertEquals(expectedEntity, entity)
  }

  @Test
  def shouldCreateIgnoredEntity() {
    // given
    val expectedEntity = ignoredKit

    // when
    val entity: XmlTestKit = loadEntityFromXml("ignoredKit", classOf[XmlTestKit])

    // then
    assertEquals(expectedEntity, entity)
  }

  @Test
  def shouldCreateSimpleCase() {
    // given
    val expectedEntity = new XmlTestCase(List(simpleTestKit, ignoredKit))

    // when
    val entity: XmlTestCase = loadEntityFromXml("simpleCase", classOf[XmlTestCase])

    // then
    assertEquals(expectedEntity, entity)
  }

  private def simpleTestKit: XmlTestKit = {
    new XmlTestKit(List("hello world", new Locale("pl")))
  }

  private def ignoredKit: XmlTestKit = {
    new XmlTestKit(Collections.emptyList(), true)
  }

  private def loadEntityFromXml[T](entityId: String, clazz: Class[T]): T = {
    val classPath = "classpath:/com/github/pushman/testini/xml/data/DataBeanDefinitionTest.xml"
    new ClassPathXmlApplicationContext(classPath).getBean(entityId, clazz)
  }
}