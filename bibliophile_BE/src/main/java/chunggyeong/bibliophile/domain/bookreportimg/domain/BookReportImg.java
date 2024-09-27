package chunggyeong.bibliophile.domain.bookreportimg.domain;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
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
public class BookReportImg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "book_report_img_id")
    private Long id;

    String imgUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "book_report_id")
    private BookReport bookReport;

    @Builder
    public BookReportImg(BookReport bookReport, String imgUrl) {
        this.bookReport = bookReport;
        this.imgUrl = imgUrl;
    }

    public static BookReportImg createBookReportImg(BookReport bookReport, String imgUrl) {
        return builder()
                .bookReport(bookReport)
                .imgUrl(imgUrl)
                .build();
    }
}
