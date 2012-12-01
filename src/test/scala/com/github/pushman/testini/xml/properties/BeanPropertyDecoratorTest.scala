package com.github.pushman.testini.xml.properties

import org.junit.Test
import scala.collection.JavaConversions._
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.junit.Assert._
import com.github.pushman.testini.test.TestEntity

class BeanPropertyDecoratorTest {

  @Test
  def shouldCreateSimpleEntity() {
    // given
    val expectedEntity = new TestEntity("entity name", Set("first name", "second name"), List("first name", "second name"))

    // when
    val entity = loadEntityFromXml("simpleEntity")

    // then
    assertEquals(expectedEntity, entity)
  }

  @Test
  def shouldCreateEntityWithReferenceInCollection() {
    // given
    val expectedEntity = new TestEntity("entity name", Set("first name", "string bean content"), List())

    // when
    val entity = loadEntityFromXml("entityWithReferenceInCollection")

    // then
    assertEquals(expectedEntity, entity)
  }

  private def loadEntityFromXml(entityId: String): TestEntity = {
    new ClassPathXmlApplicationContext("classpath:/com/github/pushman/testini/xml/properties/BeanPropertyDecoratorTest.xml").getBean(entityId, classOf[TestEntity])
  }
}
