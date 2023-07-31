package com.gu.nathaniel

import org.scalatest.flatspec.AnyFlatSpec

class FlatSpecExample extends AnyFlatSpec {

    "An Empty List" should "Have size 0" in {
      assert(List.empty.size == 0)
    }

    it should "Throw and IndexOutOfBoundException when attempting to access any elements" in {

      val emptyList = List()
      assertThrows[IndexOutOfBoundsException] {
        emptyList(0)
      }
    }

    "An Non Empty List" should "Have correct size" in {
      assert(List("1", "2", "3").size == 3)
    }

    it should "Throw and IndexOutOfBoundException when attempting to access any elements" in {

      val list = List(1, 2, 3)
      assertThrows[IndexOutOfBoundsException] {
        list(4)
      }
    }
}
