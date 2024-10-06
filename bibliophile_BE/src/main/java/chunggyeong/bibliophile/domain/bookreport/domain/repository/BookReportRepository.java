package chunggyeong.bibliophile.domain.bookreport.domain.repository;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookReportRepository extends JpaRepository<BookReport, Long> {

    Optional<BookReport> findByMyBook(MyBook myBook);

    // 특정 유저가 소유한 MyBook에 관련된 BookReport가 있는지 확인
    @Query("SELECT CASE WHEN COUNT(br) > 0 THEN TRUE ELSE FALSE END " +
            "FROM BookReport br JOIN br.myBook mb " +
            "WHERE mb.user = :user AND mb = :myBook")
    boolean existsByUserAndMyBook(@Param("user") User user, @Param("myBook") MyBook myBook);
}
