package main.java.com.library.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "book_description")
    private String bookDescription;
    private String author;
    private String genre;
    @Column(name = "added_on")
    private LocalDateTime addedOn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
