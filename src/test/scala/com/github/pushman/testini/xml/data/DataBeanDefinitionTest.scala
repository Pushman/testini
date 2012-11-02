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
    val entity = loadEntityFromXml("simpleKit", classOf[TestKitData])

    // then
    assertEquals(expectedEntity, entity)
  }

  @Test
  def shouldCreateIgnoredEntity() {
    // given
    val expectedEntity = ignoredKit

    // when
    val entity: TestKitData = loadEntityFromXml("ignoredKit", classOf[TestKitData])

    // then
    assertEquals(expectedEntity, entity)
  }

  @Test
  def shouldCreateSimpleCase() {
    // given
    val expectedEntity = new TestCaseData(List(simpleTestKit, ignoredKit))

    // when
    val entity: TestCaseData = loadEntityFromXml("simpleCase", classOf[TestCaseData])

    // then
    assertEquals(expectedEntity, entity)
  }

  private def simpleTestKit: TestKitData = {
    new TestKitData(List("hello world", new Locale("pl")))
  }

  private def ignoredKit: TestKitData = {
    new TestKitData(Collections.emptyList(), true)
  }

  private def loadEntityFromXml[T](entityId: String, clazz: Class[T]): T = {
    val classPath = "classpath:/com/github/pushman/testini/xml/data/DataBeanDefinitionTest.xml"
    new ClassPathXmlApplicationContext(classPath).getBean(entityId, clazz)
  }
}
