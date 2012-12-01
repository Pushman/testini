package com.github.pushman.testini.xml.data

import org.springframework.beans.factory.xml.{ParserContext, AbstractSingleBeanDefinitionParser}
import org.w3c.dom.Element
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import com.github.pushman.testini.xml.XmlTools

class CaseBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

  override def getBeanClassName(element: Element) = classOf[TestCaseData].getName

  override def doParse(element: Element, parserContext: ParserContext, bean: BeanDefinitionBuilder) {
    val propertyValue = getPropertyValue(parserContext, element, bean)
    bean.addPropertyValue(TestCaseData.TestKits, propertyValue)
    XmlTools.setProperty(TestKitData.Ignore, element, bean)
  }

  private def getPropertyValue(parserContext: ParserContext, element: Element, bean: BeanDefinitionBuilder) = {
    XmlTools.getPropertyValue(parserContext, element, bean.getBeanDefinition)
  }
}