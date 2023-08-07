package com.gu.nathaniel.scalamock

import com.gu.nathaniel.scalatest.model.{Customer, Order}
import com.gu.nathaniel.scalatest.{CustomerService, FilterByDate, IdentityService, OrderService}
import org.scalamock.matchers.ArgCapture.CaptureOne
import org.scalamock.scalatest.MockFactory
import org.scalatest.wordspec.AnyWordSpec

class ScalaMockDemo extends AnyWordSpec with MockFactory {

   val customer = Customer("Mr Tambourine Man", 1965)

  "Argument matching" should {
    "call identity service" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      (mockIdentityService.getCustomerById _).expects(userId).returning(None)
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)
      customerService.getOrdersForCustomer(userId)
      succeed
    }

    "call order service with no filter" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]

      (mockIdentityService.getCustomerById _).expects(userId).returns(Some(customer))
      (mockOrderService.getOrdersForCustomer _).expects(customer, None).returns(List(Order(1, "Maroccan Tambour", 27.50), Order(2, "9 Ounce Baggie", 82.75)))
     val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      customerService.getOrdersForCustomer(userId)
      succeed
    }

    "call order service with  filter" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val filter = FilterByDate("blah")

      (mockIdentityService.getCustomerById _).expects(userId).returns(Some(customer))
      (mockOrderService.getOrdersForCustomer _).expects(customer, Some(filter)).returns(List(Order(13, "Maroccan Tambour", 27.50), Order(12, "9 Ounce Baggie", 82.75)))
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      customerService.getOrdersForCustomer(userId, Some(filter))
      succeed
    }

    "not call order service without Id" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val filter = FilterByDate("blah")

      (mockIdentityService.getCustomerById _).expects(userId).returns(None)
    //  (mockOrderService.getOrdersForCustomer _).expects(customer, Some(filter)).returns(List(Order("Maroccan Tambour", 27.50), Order("9 Ounce Baggie", 82.75)))
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      customerService.getOrdersForCustomer(userId, Some(filter))
      succeed
    }

    "Call services regardless of order" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val filter = FilterByDate("blah")

      inAnyOrder {
        (mockOrderService.getOrdersForCustomer _).expects(customer, Some(filter)).returns(List(Order(123, "Maroccan Tambour", 27.50), Order(123456, "9 Ounce Baggie", 82.75)))
        (mockIdentityService.getCustomerById _).expects(userId).returns(Some(customer))
      }
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      customerService.getOrdersForCustomer(userId, Some(filter))
      succeed
    }

    "Check order if calls " in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val filter = FilterByDate("blah")

      inSequence{
        (mockIdentityService.getCustomerById _).expects(userId).returns(Some(customer))
        (mockOrderService.getOrdersForCustomer _).expects(customer, Some(filter)).returns(List(Order(123, "Maroccan Tambour", 27.50), Order(1234, "9 Ounce Baggie", 82.75)))
      }
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      customerService.getOrdersForCustomer(userId, Some(filter))
      succeed
    }

    "use times to verify function calls with filter" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val filter = FilterByDate("blah")

      (mockIdentityService.getCustomerById _).expects(userId).returns(Some(customer)).once()
      (mockOrderService.getOrdersForCustomer _).expects(customer, Some(filter)).returns(List(Order(1, "Maroccan Tambour", 27.50), Order(2, "9 Ounce Baggie", 82.75))).once()

      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      customerService.getOrdersForCustomer(userId, Some(filter))
      succeed
    }

    "use times to verify function calls with no filter" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val filter = FilterByDate("blah")

      (mockIdentityService.getCustomerById _).expects(userId).returns(None).once()
      (mockOrderService.getOrdersForCustomer _).expects(*, *).never()
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      customerService.getOrdersForCustomer(userId, Some(filter))
      succeed
    }

    "handle an exception " in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val filter = FilterByDate("blah")

      (mockIdentityService.getCustomerById _).expects(userId).throwing(new RuntimeException("Id service blew up"))
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      assertThrows[RuntimeException] {
        customerService.getOrdersForCustomer(userId, Some(filter))
      }
    }

    "Capture arguments" in {
      val userId = "mrtambourineman"
      val mockIdentityService = mock[IdentityService]
      val mockOrderService = mock[OrderService]
      val filter = FilterByDate("blah")
      val customerService = new CustomerService(mockIdentityService,  mockOrderService)

      val userIdCaptor = CaptureOne[String]()
      val userCaptor = CaptureOne[Customer]()
      val filterCaptor = CaptureOne[Option[FilterByDate]]()

      (mockIdentityService.getCustomerById _).expects(capture(userIdCaptor)).returns(Some(customer))
      (mockOrderService.getOrdersForCustomer _).expects(capture(userCaptor), capture(filterCaptor)).returns(List(Order(1, "Maroccan Tambour", 27.50), Order(2, "9 Ounce Baggie", 82.75))).once()

      customerService.getOrdersForCustomer(userId, Some(filter))
      assert(userIdCaptor.value === userId )
      assert(userCaptor.value == customer)
      assert(filterCaptor.value === Some(filter))
    }
  }

}
