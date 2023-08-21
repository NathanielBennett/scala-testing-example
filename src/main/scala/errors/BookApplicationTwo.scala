package errors

object BookApplicationTwo extends App {
  val bookDao = new BookDaoImpl
  val bookService = new BookServiceImpl(bookDao)

  bookService.getMaybeBook(100) match {
    case Some(book) => println(s"Got a book called ${book.title} back")
    case None => println("Something when wrong: message ?")

  }
}
