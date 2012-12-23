package com.github.pushman.testini.utils

import org.springframework.util.StringUtils

object StringOption {

  def apply(s: String): Option[String] =
    if (StringUtils.hasLength(s)) Some(s) else None
}