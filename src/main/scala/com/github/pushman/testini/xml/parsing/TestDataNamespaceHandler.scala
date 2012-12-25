package com.github.pushman.testini.xml.parsing

import org.springframework.beans.factory.xml.NamespaceHandlerSupport

class TestDataNamespaceHandler extends NamespaceHandlerSupport {

  def init() {
    registerBeanDefinitionParser("case", new CaseBeanDefinitionParser)
    registerBeanDefinitionParser("kit", new KitBeanDefinitionParser)
  }
}