package chunggyeong.bibliophile.domain.bookreport.domain.repository;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReportRepository extends JpaRepository<BookReport, Long> {
}
