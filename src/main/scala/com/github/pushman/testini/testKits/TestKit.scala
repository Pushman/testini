package com.github.pushman.testini.testKits

trait TestKit {

  def data: Seq[AnyRef]

  def ignore: Boolean
}

case class TestKitImpl(data: Seq[AnyRef]) extends TestKit {

  def ignore = false
}