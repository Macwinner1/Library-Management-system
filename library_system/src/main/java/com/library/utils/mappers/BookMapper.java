package main.java.com.library.utils.mappers;


import main.java.com.library.data.models.Book;
import main.java.com.library.dto.book.BookCreateOrUpdateDto;
import main.java.com.library.dto.book.BookDto;
import main.java.com.library.dto.book.BorrowBookDto;

public class BookMapper {
    public static BookDto mapToBookDto(BookDto bookDto, Book bookEntity){
        bookDto.setBookTitle(bookEntity.getBookTitle());
        bookDto.setBookId(bookEntity.getBookId());
        bookDto.setBookDescription(bookEntity.getBookDescription());
        bookDto.setAuthor(bookEntity.getAuthor());
        bookDto.setGenre(bookEntity.getGenre());
        bookDto.setAddedOn(bookEntity.getAddedOn());
        return bookDto;
    }

    public static Book mapToBookEntity(BorrowBookDto borrowBookDto, Book bookEntity){
        bookEntity.setBookId(borrowBookDto.getBookId());
        bookEntity.setBookTitle(borrowBookDto.getBookTitle());
        bookEntity.setBookDescription(borrowBookDto.getBookDescription());
        bookEntity.setAuthor(borrowBookDto.getAuthor());
        bookEntity.setGenre(borrowBookDto.getGenre());
        return bookEntity;
    }

    public static Book mapToBookEntity(BookCreateOrUpdateDto bookDto, Book bookEntity){
        bookEntity.setBookTitle(bookDto.getBookTitle());
        bookEntity.setBookDescription(bookDto.getBookDescription());
        bookEntity.setAuthor(bookDto.getAuthor());
        bookEntity.setGenre(bookDto.getGenre());
        bookEntity.setAddedOn(bookDto.getAddedOn());
        return bookEntity;
    }
}
