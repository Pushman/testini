package com.github.pushman.testini.utils

import org.junit.runner.Description

object ExtendedDescription {
  implicit def extendDescription(description: Description): ExtendedDescription = new ExtendedDescription(description)

  implicit def contractDescription(extended: ExtendedDescription): Description = extended.description
}

class ExtendedDescription(val description: Description) {

  def ++>(children: Iterable[Description]) = {
    for (child <- children)
      description.addChild(child)
    this
  }
}