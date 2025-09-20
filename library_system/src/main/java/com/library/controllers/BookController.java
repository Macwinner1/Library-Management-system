package main.java.com.library.controllers;



import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import main.java.com.library.data.models.Book;
import main.java.com.library.data.models.User;
import main.java.com.library.dto.BaseResponseDto;
import main.java.com.library.dto.book.BookCreateOrUpdateDto;
import main.java.com.library.dto.book.BookDto;
import main.java.com.library.dto.book.BookResponseDto;
import main.java.com.library.dto.book.BorrowBookDto;
import main.java.com.library.services.BookServices;
import main.java.com.library.services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class BookController {
    public final BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }
    @GetMapping("/books")
    public ResponseEntity<BookResponseDto<List<BookDto>>> fetchAllBooks() {
        List<BookDto> books = bookServices.fetchAllBooks();
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new BookResponseDto<List<BookDto>>(
                        HttpStatus.OK,
                        "Books Retrieved Successfully",
                        books
                )
        );
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<BookResponseDto<BookDto>> fetchBookByBookId(@PathVariable Long bookId){
        BookDto book = bookServices.fetchBookByBookId(bookId);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new BookResponseDto<BookDto>(
                        HttpStatus.OK,
                        "Book Fetched Successfully",
                        book
                )
        );
    }
    @PostMapping("/books/create")
    public ResponseEntity<BookResponseDto<BookDto>> createBook(@Valid @RequestBody BookCreateOrUpdateDto bookDto){
        BookDto book = bookServices.createBook(bookDto);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                new BookResponseDto<BookDto>(
                        HttpStatus.CREATED,
                        "Book Created Successfully",
                        book
                )
        );
    }
    @PutMapping("/books/{bookId}")
    public ResponseEntity<BookResponseDto<BookDto>> updateBook(@PathVariable Long bookId,@Valid @RequestBody BookCreateOrUpdateDto bookDto){
        BookDto book = bookServices.updateBook(bookId, bookDto);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new BookResponseDto<BookDto>(
                        HttpStatus.OK,
                        "Book Updated Successfully",
                        book
                )
        );

    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<BaseResponseDto> deleteBook(@PathVariable Long bookId) {
        boolean isDeleted = bookServices.deleteBook(bookId);
        if(isDeleted) {
            return ResponseEntity.status(
                    HttpStatus.NO_CONTENT
            ).body(
                    new BaseResponseDto(
                            HttpStatus.NO_CONTENT,
                            "Book Deleted Successfully"
                    )
            );
        }
        return ResponseEntity.status(
                HttpStatus.EXPECTATION_FAILED
        ).body(
                new BaseResponseDto(
                        HttpStatus.EXPECTATION_FAILED,
                        "Book Deleted Unsuccessfully"
                )
        );
    }
    @PostMapping("/books/create/bulk")
    public ResponseEntity<BookResponseDto<List<BookDto>>> bulkCreateBooks(@RequestBody List<BookCreateOrUpdateDto> booksData) {

        List<BookDto> books = bookServices.bulkCreateBooks(booksData);


        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BookResponseDto<List<BookDto>>(
                            HttpStatus.BAD_REQUEST,
                            "No new books were created (books might already exist)",
                            books
                    ));
        }


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BookResponseDto<List<BookDto>>(
                        HttpStatus.CREATED,
                        "Books created successfully",
                        books
                ));
    }
    @GetMapping("/search")
    public ResponseEntity<BookResponseDto<List<BookDto>>> searchBooks(@RequestParam String q) {
        List<BookDto> books = bookServices.searchBooks(q);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new BookResponseDto<List<BookDto>>(
                        HttpStatus.OK,
                        "Books retrieved successfully",
                        books
                )
        );
    }

    @PostMapping("/borrow")
    public ResponseEntity<BookResponseDto<Book>> borrowBook(@Valid @RequestBody BorrowBookDto borrowBookDto, HttpSession session){
        Book bookDto = bookServices.borrowBook(borrowBookDto, session);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                new BookResponseDto<Book>(
                        HttpStatus.CREATED,
                        "Book Borrowed Successfully",
                        bookDto
                )
        );
    }
}
