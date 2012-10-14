package com.github.pushman.testini.xml.properties

import org.springframework.beans.factory.xml.{BeanDefinitionDecorator, ParserContext}
import org.w3c.dom.{Node, Element}
import org.springframework.beans.factory.config.BeanDefinitionHolder

class NamePropertyDefinitionParser extends BeanDefinitionDecorator {

  override def decorate(node: Node, bean: BeanDefinitionHolder, parserContext: ParserContext): BeanDefinitionHolder =
    node match {
      case element: Element => doDecorate(element, bean, parserContext)
      case _ => throw new ClassCastException
    }

  def doDecorate(element: Element, bean: BeanDefinitionHolder, parserContext: ParserContext) = {
    val name: String = element.getAttribute("que")
    bean.getBeanDefinition.getPropertyValues.add("name", name)
    bean
  }
}

