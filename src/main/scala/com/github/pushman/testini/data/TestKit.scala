package com.github.pushman.testini.data

trait TestKit {

  def data: Seq[AnyRef]

  def ignore: Boolean
}