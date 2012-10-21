package com.github.pushman.testini.util


object TraversableUtils {

  implicit def ToTraversableUtils[A](a: Traversable[A]) =
    new ExtendedTraversable[A] {
      def findSome[B](f: (A) => Option[B]) = TraversableUtils.findSome(a, f)
    }

  def findSome[A, B](source: Traversable[A], f: (A) => Option[B]): Option[B] =
    source match {
      case Nil => None
      case head :: tail =>
        f(head) match {
          case Some(result) => Some(result)
          case None => findSome(tail, f)
        }
    }
}

trait ExtendedTraversable[A] {

  def findSome[B](f: (A) => Option[B]): Option[B]
}