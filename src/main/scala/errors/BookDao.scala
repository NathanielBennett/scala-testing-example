package errors

import errors.model.{BookApplicationError, BookDaoObject, IllegalBookError, TerroristSympathisingGenderCriticalBookError, WokeAuthorError}

trait BookDao {
  def getBook(id: Long): BookDaoObject
  def getBookEither(id: Long): Either[BookApplicationError, BookDaoObject]
}

class BookDaoImpl extends BookDao {
  def getBook(id: Long) = id match  {
    case 999L => throw new IllegalStateException("This is a socialist text and should be burnt")
    case 666 => throw new IllegalArgumentException("Don't argue with us")
    case 22 => throw new RuntimeException("Don't do that, it's naughty!")

    case _ => BookDaoObject(id, "American Psycho", "Brett Easton Ellis", "ISBN 666 00 666 666")
  }

  override def getBookEither(id: Long): Either[BookApplicationError, BookDaoObject] = id match {
    case 111 => Left(IllegalBookError("This book indoctrinates children"))
    case 11 => Left(WokeAuthorError("This isn't art, it's propgranda"))
    case 222 => Left(TerroristSympathisingGenderCriticalBookError("The kids need protecting from this"))
    case _ => Right(BookDaoObject(id, "American Psycho", "Brett Easton Ellis", "ISBN 666 00 666 666"))

  }
}



