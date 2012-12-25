package com.github.pushman.testini.xml.parsing

import org.springframework.beans.factory.xml.{ParserContext, AbstractSingleBeanDefinitionParser}
import org.w3c.dom.Element
import org.springframework.beans.factory.support.BeanDefinitionBuilder
import com.github.pushman.testini.xml.XmlTools
import com.github.pushman.testini.xml.data.XmlTestKit

class KitBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

  override def getBeanClassName(element: Element) =
    classOf[XmlTestKit].getName

  override def doParse(element: Element, parserContext: ParserContext, bean: BeanDefinitionBuilder) {
    val propertyValue = XmlTools.getNestedBeans(parserContext, element, bean.getBeanDefinition)
    bean.addPropertyValue(XmlTestKit.Data, propertyValue)
    XmlTools.getBoolean("ignore", element).collect {
      case isIgnored => XmlTools.setProperty(XmlTestKit.IsIgnored, isIgnored, bean)
    }
  }
}