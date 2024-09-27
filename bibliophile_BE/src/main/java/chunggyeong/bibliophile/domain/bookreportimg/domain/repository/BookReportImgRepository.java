package chunggyeong.bibliophile.domain.bookreportimg.domain.repository;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
import chunggyeong.bibliophile.domain.bookreportimg.domain.BookReportImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReportImgRepository extends JpaRepository<BookReportImg, Long> {
    boolean existsByBookReport(BookReport bookReport);

    void deleteAllByBookReport(BookReport bookReport);
}
