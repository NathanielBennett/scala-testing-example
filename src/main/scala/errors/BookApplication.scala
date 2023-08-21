package errors

object BookApplication extends App {

  val bookDao = new BookDaoImpl
  val bookService = new BookServiceImpl(bookDao)

  try {
    val b = bookService.getBookById(22)
  } catch {
    case ie: IllegalStateException => println("Caught illegal state: ")
    case ix: IllegalArgumentException => println("Illegal argument")
    case _ => println("Some other wierd shit happened")
  }

}
