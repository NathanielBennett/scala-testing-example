package com.gu.nathaniel

import org.scalatest.funspec.AnyFunSpec

class AnyFunSpecExample extends AnyFunSpec {

  describe("A list") {
    describe("When empty") {
      it("Should have size 0") {
        assert(List.empty.size == 0)
      }

      it("should throw an IndexOutOfBoundsException when to access an element") {
        val emptyList = List()
        assertThrows[IndexOutOfBoundsException] {
          emptyList(3)
        }
      }
    }

    describe("When  non empty") {
      val list = List(1, 2, 3, 5)

      it("Should have correct size ") {
        assert(list.size == 4)
      }

      it("should throw an IndexOutOfBoundsException when to access an element") {
        assertThrows[IndexOutOfBoundsException] {
          list(12)
        }
      }
    }



  }
}
