package com.gu.nathaniel

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import MyCustomMatchers._

import java.io.File

class CustomMatchingSpecExample extends AnyFlatSpec with Matchers {

  "A file" should "Have the right extension" in {
    val f = new File("stuff.json")
    f should endWithExtension(".json")
  }

  ignore should "Not have the wrong extension" in {
    val f = new File("dishcloth.txt")
    f should endWithExtension ("json")

  }

}
