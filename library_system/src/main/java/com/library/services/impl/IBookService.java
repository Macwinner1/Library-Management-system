package main.java.com.library.services.impl;


import jakarta.validation.Valid;
import main.java.com.library.data.models.Book;
import main.java.com.library.data.repository.BookRepository;
import main.java.com.library.dto.book.BookCreateOrUpdateDto;
import main.java.com.library.dto.book.BookDto;
import main.java.com.library.exception.BookAlreadyExistsException;
import main.java.com.library.exception.ResourceNotFoundException;

import main.java.com.library.services.BookServices;
import main.java.com.library.utils.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class IBookService implements BookServices {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDto> fetchAllBooks() {
        return bookRepository.findAll().stream().map(
                book -> {
                    return BookMapper.mapToBookDto(new BookDto(),book);

                }
        ).toList();
    }

    @Override
    public BookDto fetchBookByBookId(Long bookId){
        Book book = bookRepository.findById(
                bookId
        ).orElseThrow( () -> new ResourceNotFoundException(
                        "Book","id",bookId.toString()
                )
        );
        return BookMapper.mapToBookDto(new BookDto(),book);
    }

    @Override
    public BookDto createBook(BookCreateOrUpdateDto bookData){
        Optional<Book> optionalBook = bookRepository.findBookByBookTitleAndAuthor(bookData.getBookTitle(), bookData.getAuthor());
        if(optionalBook.isPresent()){
            throw new BookAlreadyExistsException(
                    String.format("Book already exists with the given title '%s' and author '%s'.",
                            bookData.getBookTitle(), bookData.getAuthor())
            );
        }
        Book book = bookRepository.save(BookMapper.mapToBookEntity(bookData,new Book()));
        return BookMapper.mapToBookDto(new BookDto(), book);
    }

    @Override
    public BookDto updateBook(Long bookId, @Valid BookCreateOrUpdateDto bookData) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Book","BookId",bookId.toString()
                )
        );
        BookMapper.mapToBookEntity(bookData,book);
        bookRepository.save(book);
        return BookMapper.mapToBookDto(new BookDto(),book);
    }

    @Override
    public boolean deleteBook(Long bookId) {
        bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Book","BookId",bookId.toString()
                )
        );
        bookRepository.deleteById(bookId);
        return true;
    }

    @Transactional
    @Override
    public List<BookDto> bulkCreateBooks(List<BookCreateOrUpdateDto> bookData) {
        List<BookCreateOrUpdateDto> booksToCreate = bookData.stream()
                .filter(bookDto -> !bookExists(bookDto.getBookTitle(), bookDto.getAuthor()))
                .toList();


        if (booksToCreate.isEmpty()) {
            return List.of();
        }
        List<Book> books = booksToCreate.stream().map(
                bookDto -> BookMapper.mapToBookEntity(bookDto, new Book())
        ).toList();
        List<Book> savedBook = bookRepository.saveAll(books);
        return savedBook.stream().map(
                book -> BookMapper.mapToBookDto(new BookDto(), book)
        ).toList();
    }

    @Override
    public List<BookDto> searchBooks(String q) {
        return bookRepository.searchBooks(q).stream().map(
                book -> BookMapper.mapToBookDto(new BookDto(), book)
        ).toList();
    }

    private boolean bookExists(String title, String author) {
        Optional<Book> optionalBook = bookRepository.findBookByBookTitleAndAuthor(title, author);
        return optionalBook.isPresent();
    }
}
