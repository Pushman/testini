package com.github.pushman.testini.util

import org.junit.runner.Description

object ExtendedDescription {
  implicit def extendDescription(description: Description): ExtendedDescription = new ExtendedDescription(description)

  implicit def contractDescription(extended: ExtendedDescription): Description = extended.description
}

class ExtendedDescription(val description: Description) {

  def ++>(children: Iterable[Description]) = {
    children.foreach(description.addChild(_))
    this
  }
}
