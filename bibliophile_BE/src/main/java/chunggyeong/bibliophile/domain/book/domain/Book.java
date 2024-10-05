package chunggyeong.bibliophile.domain.book.domain;

import chunggyeong.bibliophile.domain.review.domain.Review;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Book {

    @Id
    @Column(name = "book_id")
    private Long id;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String contents;

    private String isbn;
    private String kdc;
    private String title;
    private String authors;
    private int page;
    private String thumbnail;
    private String publisher;

    @Builder
    public Book(Long id, String contents, String isbn, String kdc, String title, String authors, int page, String thumbnail, String publisher) {
        this.id = id;
        this.contents = contents;
        this.isbn = isbn;
        this.kdc = kdc;
        this.title = title;
        this.authors = authors;
        this.page = page;
        this.thumbnail = thumbnail;
        this.publisher = publisher;
    }

    public static Book createBook(String contents, String isbn, String kdc, String title, String authors, int page, String thumbnail, String publisher) {
        return builder()
                .contents(contents)
                .isbn(isbn)
                .kdc(kdc)
                .title(title)
                .authors(authors)
                .page(page)
                .thumbnail(thumbnail)
                .publisher(publisher)
                .build();
    }
}
