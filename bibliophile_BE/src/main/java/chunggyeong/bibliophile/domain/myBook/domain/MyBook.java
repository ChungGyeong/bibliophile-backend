package chunggyeong.bibliophile.domain.myBook.domain;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
import chunggyeong.bibliophile.domain.memo.domain.Memo;
import chunggyeong.bibliophile.domain.myBook.exception.PageLimitExceededException;
import chunggyeong.bibliophile.domain.myBook.exception.UserNotMyBookHostException;
import chunggyeong.bibliophile.domain.timer.domain.Timer;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.database.BaseEntity;
import chunggyeong.bibliophile.global.utils.DurationConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus.READING;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MyBook extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "my_book_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private int readingPage;

    @Convert(converter = DurationConverter.class)
    private Duration totalReadingTime;
    private LocalDateTime completionReadingTime;

    @Enumerated(STRING)
    private ReadingStatus readingStatus;

    @OneToMany(mappedBy = "myBook", cascade = CascadeType.ALL)
    private List<Timer> timerList = new ArrayList<>();

    @OneToMany(mappedBy = "myBook", cascade = CascadeType.ALL)
    private List<BookReport> bookReportList = new ArrayList<>();

    @OneToMany(mappedBy = "myBook", cascade = CascadeType.ALL)
    private List<Memo> memoList = new ArrayList<>();

    @Builder
    public MyBook(User user, Book book, int readingPage, Duration totalReadingTime, ReadingStatus readingStatus) {
        this.user = user;
        this.book = book;
        this.readingPage = readingPage;
        this.totalReadingTime = totalReadingTime;
        this.readingStatus = readingStatus;
    }

    public static MyBook createMyBook(User user, Book book) {
        return builder()
                .user(user)
                .book(book)
                .readingPage(0)
                .totalReadingTime(Duration.ZERO)
                .readingStatus(ReadingStatus.READING)
                .build();
    }

    public void validUserIsHost(User user) {
        if (!this.user.equals(user)) {
            throw UserNotMyBookHostException.EXCEPTION;
        }
    }

    public void validPage(int readingPage, int bookPage) {
        if (bookPage < readingPage) {
            throw PageLimitExceededException.EXCEPTION;
        }
    }

    public void updatePage(int readingPage) {
        this.readingPage = readingPage;
    }

    public void updateReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
    }

    public void updateCompletionReadingTime() {
        this.completionReadingTime = LocalDateTime.now();
    }

    public void updateTotalReadingTime(Duration totalReadingTime) {
        this.totalReadingTime = totalReadingTime;
    }

    public void reReadBook() {
        this.readingStatus = READING;
        this.readingPage = 0;
        this.totalReadingTime = Duration.ZERO;
        this.completionReadingTime = null;
    }
}
