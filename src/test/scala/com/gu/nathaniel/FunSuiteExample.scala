package com.gu.nathaniel

import org.scalatest.funsuite.AnyFunSuite

class FunSuiteExample extends AnyFunSuite {


  test("An empty list should have a size") {
     assert(List.empty.size == 0)
  }

  test("A list should have the correct size") {
    assert(List("One", "Two", "Threes").size == 3)
  }

  test( "Accessing invalid index should throw IndexOutOfBoundsException") {
     val fruit = List("Peach", "Pear", "Plum")
     assert(fruit.head == "Peach")
     assert(fruit.headOption == Some("Peach") )
     assertThrows[IndexOutOfBoundsException] {
       fruit(7)
     }
  }

}
