package com.gu.nathaniel

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class FlatSpecMatchingExample extends AnyFlatSpec with Matchers {

  val list = List(1,2,3,4,5)
  val emptyList = List()

  "A list" should "Be check0a0ble for equality" in {
      list.headOption should equal(Some(1))
      emptyList.headOption shouldEqual None
  }

  it should "be checkable for equality with be" in {
    list.headOption should be (Some(1))
    emptyList.headOption shouldBe None
  }

  it should "Check the size of collextions" in {
    list.size shouldBe 5
    list should have size(5)
  }

  it should "let us check that an email is valid" in {

    "nathaniel.bennett@theguardian.com" should fullyMatch regex """[^@]+@[^\.]+\..+"""
  }


  "A String" should "be able to use matchers in" in {
    val s = "Nathaniel is a scala god"
    s should startWith ("Nathaniel")
    s should include ("god")
    s should not include("God")
    s should have length 24
  }

  "A list" should "be checkable for emptyness" in {
     val l: List[String] = List()
     l shouldBe empty
     l shouldBe emptyList

     val lother = List("Three", "Two", "One")
     lother should not be empty
     lother should contain ("Two")
     lother should have size 3
  }

}
