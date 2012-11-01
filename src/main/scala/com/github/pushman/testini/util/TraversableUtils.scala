package com.github.pushman.testini.util


object TraversableUtils {

  implicit def asExtendedSeq[A](a: Seq[A]) = new ExtendedList(a)

  def findSome[A, B](source: List[A], f: (A) => Option[B]): Option[B] =
    source match {
      case Nil => None
      case head :: tail =>
        f(head) match {
          case Some(result) => Some(result)
          case None => findSome(tail, f)
        }
    }

  def iterator[A, B](iterable: Iterable[A], f: (A) => B): Iterator[B] =
    new Iterator[B] {
      val iterator = iterable.iterator

      override def hasNext = iterator.hasNext

      override def next() = f(iterator.next())
    }
}

case class ExtendedList[A](underlying: Seq[A]) {

  def findSome[B](f: (A) => Option[B]): Option[B] = TraversableUtils.findSome(underlying.toList, f)
}