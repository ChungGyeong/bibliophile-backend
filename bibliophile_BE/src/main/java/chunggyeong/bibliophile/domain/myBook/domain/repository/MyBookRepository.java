package chunggyeong.bibliophile.domain.myBook.domain.repository;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;
import chunggyeong.bibliophile.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {

    List<MyBook> findAllByUserAndReadingStatus(User user, ReadingStatus readingStatus);

    boolean existsByUserAndBook(User user, Book book);

    Optional<MyBook> findByUserAndBook(User user, Book book);

    List<MyBook> findAllByUser(User user);
}
