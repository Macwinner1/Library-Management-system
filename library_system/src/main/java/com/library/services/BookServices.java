package main.java.com.library.services;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import main.java.com.library.data.models.Book;
import main.java.com.library.dto.book.BookCreateOrUpdateDto;
import main.java.com.library.dto.book.BookDto;
import main.java.com.library.dto.book.BorrowBookDto;

import java.util.List;

public interface BookServices {
    List<BookDto> fetchAllBooks();
    BookDto fetchBookByBookId(Long bookId);
    BookDto createBook(BookCreateOrUpdateDto bookData);
    BookDto updateBook(Long bookId, @Valid BookCreateOrUpdateDto bookData);
    boolean deleteBook(Long bookId);
    List<BookDto> bulkCreateBooks(List<BookCreateOrUpdateDto> bookData);
    List<BookDto> searchBooks(String q);
    Book borrowBook(BorrowBookDto borrowBookDto, HttpSession session);
}
