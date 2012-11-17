package com.github.pushman.testini.testKits

trait TestKit {

  def data: Seq[AnyRef]

  def ignore: Boolean
}