package errors

import scala.util.{Failure, Success}

object BookAppThree extends App {

  val bookDao = new BookDaoImpl
  val bookService = new BookServiceImpl(bookDao)


  // Map try?
  val result = bookService.getBookByIdTry(999) match {
    case Success(book) => s"Got a book called ${book.title} back"
    case Failure(exception) => s"Something when wrong: message: '${exception.getMessage}'"
  }
  println(s"Result: $result")
}
