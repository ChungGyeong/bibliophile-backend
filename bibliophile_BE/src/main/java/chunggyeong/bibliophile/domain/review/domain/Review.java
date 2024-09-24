package chunggyeong.bibliophile.domain.review.domain;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.myBook.exception.UserNotMyBookHostException;
import chunggyeong.bibliophile.domain.review.exception.UserNotReviewHostException;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private String content;
    private int star;

    @Builder
    public Review(User user, Book book, String content, int star) {
        this.user = user;
        this.book = book;
        this.content = content;
        this.star = star;
    }

    public static Review createReview(User user, Book book, String content, int star) {
        return builder()
                .user(user)
                .book(book)
                .content(content)
                .star(star)
                .build();
    }

    public void updateReview(String content, int star) {
        this.content = content;
        this.star = star;
    }

    public void validUserIsHost(User user) {
        if (!this.user.equals(user)) {
            throw UserNotReviewHostException.EXCEPTION;
        }
    }
}