package com.gu.nathaniel.mockito

import com.gu.nathaniel.scalatest.{CustomerService, FilterByDate, IdentityService, OrderFilter, OrderService}
import com.gu.nathaniel.scalatest.model.Customer
import org.mockito.{ArgumentCaptor, Mockito}
import org.mockito.Mockito._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.mockito

class ScalaMockitoDemo extends AnyWordSpec with MockitoSugar with Matchers {

  val customer = Customer("Mr Tambourine Man", 1965)

  "Mockito" should {
    "call identity ser0vice" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      when(mockIdentityService.getCustomerById(userId)).thenReturn(Some(customer))
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)
      customerService.getOrdersForCustomer(userId)

      verify(mockIdentityService).getCustomerById(userId)
    }

    "Call the order serv1ice without a filter" in  {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      when(mockIdentityService.getCustomerById(userId)).thenReturn(Some(customer))
      customerService.getOrdersForCustomer(userId)

      verify(mockIdentityService).getCustomerById(userId)
      verify(mockOrderService).getOrdersForCustomer(customer, None)
    }

    "Call the order serv1ice with a filter" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)
      val filter = FilterByDate("blah")

      when(mockIdentityService.getCustomerById(userId)).thenReturn(Some(customer))
      customerService.getOrdersForCustomer(userId, Some(filter))

      verify(mockIdentityService).getCustomerById(userId)
      verify(mockOrderService).getOrdersForCustomer(customer, Some(filter) )
    }

    "not call order service without Id" in  {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)
      val filter = FilterByDate("blah")

      when(mockIdentityService.getCustomerById(userId)).thenReturn(None)
      customerService.getOrdersForCustomer(userId, Some(filter))

      verify(mockIdentityService).getCustomerById(userId)
    }

    "Verify the order of mocked calls" in  {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)
      val filter = FilterByDate("blah")
      val inOrder = Mockito.inOrder(mockIdentityService, mockOrderService)

      when(mockIdentityService.getCustomerById(userId)).thenReturn(Some(customer))
      customerService.getOrdersForCustomer(userId, Some(filter))

      inOrder.verify(mockIdentityService).getCustomerById(userId)
      inOrder.verify(mockOrderService).getOrdersForCustomer(customer, Some(filter) )
    }

    "Count the number of times a method is called" in  {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val customerService = new CustomerService(mockIdentityService, mockOrderService)

      when(mockIdentityService.getCustomerById(userId)).thenReturn(Some(customer))
      customerService.getOrdersForCustomer(userId)

      verify(mockIdentityService, times(1)).getCustomerById(userId)
      verify(mockOrderService, times(1)).getOrdersForCustomer(customer, None)
    }
11
    "Verify zero interactions called" in  {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val customerService = new CustomerService(mockIdentityService, mockOrderService)

      when(mockIdentityService.getCustomerById(userId)).thenReturn(None)
      customerService.getOrdersForCustomer(userId)

      verify(mockIdentityService, times(1)).getCustomerById(userId)
      verifyNoInteractions(mockOrderService)
    }

    "Test for exceptions" ignore  {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val customerService = new CustomerService(mockIdentityService, mockOrderService)

      when(mockIdentityService.getCustomerById(userId)).thenThrow(new IllegalStateException("This is a coup"))

      assertThrows[IllegalStateException] {
        customerService.getOrdersForCustomer(userId)
      }
    }

  "Argument Capturing" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val customerService = new CustomerService(mockIdentityService, mockOrderService)

      val userIdCaptor = ArgumentCaptor.forClass(classOf[String])

      val customerCaptor = ArgumentCaptor.forClass(classOf[Customer])
      val fiterCaptor = ArgumentCaptor.forClass(classOf[Option[OrderFilter]])

      when(mockIdentityService.getCustomerById(userId)).thenReturn(Some(customer))
      customerService.getOrdersForCustomer(userId)
      verify(mockIdentityService, times(1)).getCustomerById(userIdCaptor.capture())
      verify(mockOrderService, times(1)).getOrdersForCustomer(customerCaptor.capture(), fiterCaptor.capture())

      userIdCaptor.getValue shouldEqual userId
      customerCaptor.getValue shouldBe customer
      fiterCaptor.getValue shouldEqual None
    }
  }
}
