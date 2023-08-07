package com.gu.nathaniel.scalatest

import com.gu.nathaniel.scalatest.model.Customer

trait IdentityService {
    def getCustomerById(customerId: String): Option[Customer] = None
}
