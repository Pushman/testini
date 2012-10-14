package com.github.pushman.testini.xml

import data.TestKitData
import org.springframework.beans.factory.xml.ParserContext
import org.w3c.dom.Element
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.util.StringUtils

object XmlTools {
  def getPropertyValue(parserContext: ParserContext, element: Element, bean: BeanDefinition) = {
    parserContext.getDelegate.parseListElement(element, bean)
  }

  def setProperty(propertyName: String, element: Element, bean: BeanDefinitionBuilder) {
    val ignore: String = element.getAttribute(propertyName)
    if (StringUtils.hasLength(ignore)) {
      bean.addPropertyValue(TestKitData.Ignore, ignore.toBoolean)
    }
  }
}
