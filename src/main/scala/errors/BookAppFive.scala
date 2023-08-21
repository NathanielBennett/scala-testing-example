package errors

import errors.model.{BookApplicationError, IllegalBookError, WokeAuthorError}

object BookAppFive extends App {
  val bookDao = new BookDaoImpl
  val bookService = new BookServiceImpl(bookDao)

  bookService.getBookEither(222) match {
    case Right(book) => println(s"Got a book: ${book}")
    case Left(error) => handleErrorResponse(error) {
      case ibx: IllegalBookError => println(s"Illegal Book Exception: ${ibx.message}")
      case wox: WokeAuthorError => println(s"Woke Author alert: ${wox.message}")
    }
  }

  //Real wold Unit might be a wrapper for a response class
  def handleErrorResponse(error: BookApplicationError)(errorResolver: PartialFunction[BookApplicationError, Unit]): Unit = {
    val lift = errorResolver.lift
    lift(error).getOrElse(println("Something so scary that i cant deal with it happend"))
    // add type lift
  }

}
