package com.gu.nathaniel.scalatest

trait OrderFilter{
  def property: String
}

case class FilterByProductType(override val property: String) extends OrderFilter

case class FilterByDate(override val property: String) extends  OrderFilter