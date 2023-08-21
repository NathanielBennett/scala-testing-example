package errors

import errors.model.{Book, BookApplicationError}

import scala.util.Try

trait BookService {

  def getBookById(id: Long): Book
  def getBookByIdTry(id: Long): Try[Book]
  def getMaybeBook(id: Long): Option[Book]
  def getBookEither(id: Long): Either[BookApplicationError, Book]

}


class BookServiceImpl(bookDao: BookDao) extends BookService {
  override def getBookById(id: Long): Book ={
    val bdo = bookDao.getBook(id)
    Book(bdo)
 }

  override def getMaybeBook(id: Long): Option[Book] = Try(getBookById(id)).toOption

  override def getBookEither(id: Long): Either[BookApplicationError, Book] = bookDao.getBookEither(id) match {
    case Right(bd) => Right(Book(bd))
    case Left(bookError) => Left(bookError)
  }

  override def getBookByIdTry(id: Long): Try[Book] =
    Try(bookDao.getBook(id)).map( bd => Book(bd))

}
