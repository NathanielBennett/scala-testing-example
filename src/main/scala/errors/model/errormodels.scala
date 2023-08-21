package errors.model

case class BookDaoObject(id: Long,
                         title: String, author: String, isbn: String)

case class Review(stars: Int, text: String)
case class Book(title: String, author: String, isbn: String, yearOfPublication: Int, reviews:  List[Review] = List.empty)
object Book {

    def apply(bookDaoObject: BookDaoObject) : Book = {
      val reviews = List(
        Review(1, "Vapid misogyny masquerading as art"),
        Review(4, "Exquisitely written parody, paced so beautifully as to pinpoint it's mark"),
        Review(5, "Read it on the way to the varsity game. Laughed my ass off")
      )
      Book(bookDaoObject.title, bookDaoObject.author, bookDaoObject.isbn, 1996, reviews)
    }
}

//Means that the match can be exhaustive at compile time
// Extend Throwable to make try.transform work
sealed trait BookApplicationError extends Throwable {
  def message: String
}

case class IllegalBookError(message: String) extends BookApplicationError
case class WokeAuthorError(message: String) extends  BookApplicationError
case class TerroristSympathisingGenderCriticalBookError(message: String) extends BookApplicationError
