package com.gu.nathaniel

import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec

class BeforeAndAfterExampleSpec extends AnyFlatSpec with BeforeAndAfter {

  val builder = new StringBuilder()

  before {
    builder.append("Friday afternoon ")
  }

  after {
    builder.clear()
  }

  "Friday afternoon" should "not include debugging data-designs 1 in 10 projects" in {
    builder.append("should not include debugging data-designs 1 in 10 projects")
    assert(builder.toString ==="Friday afternoon should not include debugging data-designs 1 in 10 projects")
  }

  it should "Involve no last minute pull requests" in {
    builder.append("should Involve no last minute pull requests")
    assert(builder.toString == "Friday afternoon should Involve no last minute pull requests")
  }

  it should "have beer" in {
    builder.append("should have beer")
    assert(builder.toString() == "Friday afternoon should have beer")
  }

}
