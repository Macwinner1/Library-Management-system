package main.java.com.library.data.repository;


import main.java.com.library.data.models.Book;
import main.java.com.library.dto.book.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.bookTitle) = LOWER(:bookTitle) AND b.author = :author")
    Optional<Book> findBookByBookTitleAndAuthor(String bookTitle, String author);

    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.bookTitle) LIKE LOWER(CONCAT('%', :text, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :text, '%')) OR " +
            "STR(b.addedOn) LIKE CONCAT('%', :text, '%')")
    List<Book> searchBooks(@Param("text") String text);


}
