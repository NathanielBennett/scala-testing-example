package com.gu.nathaniel.scalatest

import com.gu.nathaniel.scalatest.model.{Customer, Order}

import java.time.LocalDate

class OrderService {
    def getOrdersForCustomer(customer: Customer, orderFilter: Option[OrderFilter]): List[Order] = List.empty
    def getOrderById(orderId: Long): Order = Order(1, "I will mock this", 123)

}
