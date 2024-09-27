package chunggyeong.bibliophile.domain.bookreport.domain;

import chunggyeong.bibliophile.domain.bookreportimg.domain.BookReportImg;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.exception.UserNotMyBookHostException;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.database.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BookReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "book_report_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="my_book_id")
    private MyBook myBook;

    @OneToMany(mappedBy = "bookReport", cascade = CascadeType.ALL)
    private List<BookReportImg> bookReportImgList = new ArrayList<>();

    @Builder
    public BookReport(String content, MyBook myBook) {
        this.content = content;
        this.myBook = myBook;
    }

    public static BookReport createBookReport(String content, MyBook myBook) {
        return builder()
                .content(content)
                .myBook(myBook)
                .build();
    }

    public void validUserIsHost(User user) {
        if(!this.getMyBook().getUser().equals(user)) {
            throw UserNotMyBookHostException.EXCEPTION;
        }
    }

    public void updateBookReport(String content) {
        this.content = content;
    }
}
