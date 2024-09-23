package chunggyeong.bibliophile.domain.myBook.domain.repository;

import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;
import chunggyeong.bibliophile.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyBookRepository extends JpaRepository<MyBook, Long> {

    List<MyBook> findAllByUserAndReadingStatus(User user, ReadingStatus readingStatus);
}
