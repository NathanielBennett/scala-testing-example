package com.gu.nathaniel.scalatest

import com.gu.nathaniel.scalatest.model.{Customer, Order}

import java.time.LocalDateTime

class CustomerService(identityService: IdentityService, orderService: OrderService) {
  def getOrdersForCustomer(customerId: String, filter: Option[OrderFilter] = None): List[Order] = {
    val x = identityService.getCustomerById(customerId)
    x match {
      case Some(customer) => orderService.getOrdersForCustomer(customer, filter)
      case None => List.empty
    }
  }



}