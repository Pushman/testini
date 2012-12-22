package com.github.pushman.testini.xml.properties

import org.springframework.beans.factory.xml.{ParserContext, BeanDefinitionDecorator}
import org.w3c.dom.{Element, Node}
import org.springframework.beans.factory.config.BeanDefinitionHolder
import com.github.pushman.testini.xml.XmlTools

object CollectionBeanPropertyDecorator {
  val NameAttribute = "name"
}

class CollectionBeanPropertyDecorator extends BeanDefinitionDecorator {

  override def decorate(node: Node, bean: BeanDefinitionHolder, parserContext: ParserContext): BeanDefinitionHolder =
    node match {
      case element: Element => doDecorate(element, bean, parserContext)
      case _ => throw new ClassCastException
    }

  def doDecorate(element: Element, bean: BeanDefinitionHolder, parserContext: ParserContext) = {
    val propertyName = getPropertyName(element)
    val propertyValue: java.util.List[_] = getPropertyValue(parserContext, element, bean)
    bean.getBeanDefinition.getPropertyValues.add(propertyName, propertyValue)
    bean
  }

  private def getPropertyValue(parserContext: ParserContext, element: Element, bean: BeanDefinitionHolder) = {
    XmlTools.getNestedBeans(parserContext, element, bean.getBeanDefinition)
  }

  private def getPropertyName(element: Element): String = {
    element.getAttribute(CollectionBeanPropertyDecorator.NameAttribute)
  }
}
