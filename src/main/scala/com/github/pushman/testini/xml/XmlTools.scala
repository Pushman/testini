package com.github.pushman.testini.xml

import org.springframework.beans.factory.xml.ParserContext
import org.w3c.dom.Element
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.util.StringUtils

object XmlTools {

  def getNestedBeans(parserContext: ParserContext, element: Element, bean: BeanDefinition) = {
    parserContext.getDelegate.parseListElement(element, bean)
  }

  def getBoolean(propertyName: String, element: Element) =
    getProperty(propertyName, element).map(s => s.toBoolean)

  def getProperty(propertyName: String, element: Element) =
    if (StringUtils.hasLength(element.getAttribute(propertyName)))
      Some(element.getAttribute(propertyName))
    else
      None

  def setProperty(propertyName: String, propertyValue: Boolean, bean: BeanDefinitionBuilder) {
    bean.addPropertyValue(propertyName, propertyValue)
  }
}