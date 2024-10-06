package chunggyeong.bibliophile.domain.myBook.domain.repository;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;
import chunggyeong.bibliophile.domain.user.domain.Gender;
import chunggyeong.bibliophile.domain.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {

    List<MyBook> findAllByUserAndReadingStatusOrderByCreatedDateDesc(User user, ReadingStatus readingStatus);

    boolean existsByUserAndBook(User user, Book book);

    Optional<MyBook> findByUserAndBook(User user, Book book);

    List<MyBook> findAllByUser(User user);

    @Query("SELECT mb FROM MyBook mb " +
            "JOIN mb.user u " +
            "WHERE FLOOR((YEAR(CURRENT_DATE) - YEAR(u.birthday)) / 10) = :ageGroup " +
            "AND u.gender = :gender " +
            "GROUP BY mb, mb.book, mb.completionReadingTime, mb.createdDate, mb.lastModifyDate, " +
            "mb.readingPage, mb.readingStatus, mb.totalReadingTime, mb.user " +
            "ORDER BY COUNT(mb) DESC")
    Page<MyBook> findAllByAgeAndGenderOrderByMyBookCountDesc(
            @Param("ageGroup") int ageGroup,
            @Param("gender") Gender gender,
            Pageable pageable);

}
