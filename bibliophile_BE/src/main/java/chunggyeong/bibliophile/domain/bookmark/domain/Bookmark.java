package chunggyeong.bibliophile.domain.bookmark.domain;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.bookmark.exception.UserNotBookmarkHostException;
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
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Builder
    public Bookmark(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public static Bookmark createBookmark(User user, Book book) {
        return builder()
                .user(user)
                .book(book)
                .build();
    }

    public void validUserIsHost(User user) {
        if (!this.user.equals(user)) {
            throw UserNotBookmarkHostException.EXCEPTION;
        }
    }
}
