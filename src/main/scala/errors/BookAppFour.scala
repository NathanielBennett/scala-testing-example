package errors

object appBookAppThree extends App {
  val bookDao = new BookDaoImpl
  val bookService = new BookServiceImpl(bookDao)

  bookService.getBookEither(999) match {
    case Left(err) => println(s"There was an error getting the book: ${err.message}")
    case Right(book)  => println( s"Got book: $book")
  }


}
