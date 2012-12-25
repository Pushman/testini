package com.github.pushman.testini.xml.parsing

import org.springframework.beans.factory.xml.{ParserContext, AbstractSingleBeanDefinitionParser}
import org.w3c.dom.Element
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import com.github.pushman.testini.xml.XmlTools
import com.github.pushman.testini.xml.data.XmlTestCase

class CaseBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

  override def getBeanClassName(element: Element) =
    classOf[XmlTestCase].getName

  override def doParse(element: Element, parserContext: ParserContext, bean: BeanDefinitionBuilder) {
    val propertyValue = XmlTools.getNestedBeans(parserContext, element, bean.getBeanDefinition)
    bean.addPropertyValue(XmlTestCase.TestKits, propertyValue)
  }
}