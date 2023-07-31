package com.gu.nathaniel

import org.scalatest.flatspec.AnyFlatSpec

class FlatSpecTraitExample extends AnyFlatSpec {

    trait ListTestTrait {
       val emptyList: List[String] = List.empty
       val list = List("One", "Two", "Three")
    }

    "An Empty List" should "Have size 0" in new ListTestTrait {
      assert(emptyList.size == 0)
    }

    it should "Throw and IndexOutOfBoundException when attempting to access any elements" in new ListTestTrait {

      assertThrows[IndexOutOfBoundsException] {
        emptyList(0)
      }
    }

    "An Non Empty List" should "Have correct size" in new ListTestTrait {
      assert(list.size == 3)
    }

    it should "Throw and IndexOutOfBoundException when attempting to access any elements" in new ListTestTrait {
      assertThrows[IndexOutOfBoundsException] {
        list(4)
      }
    }
}
