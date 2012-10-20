package com.github.pushman.testini.data

trait TestKit {

  def data: Seq[_]

  def ignore: Boolean
}
