package chunggyeong.bibliophile.domain.review.domain.repository;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.review.domain.Review;
import chunggyeong.bibliophile.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByBook(Book book);

    boolean existsByUserAndBook(User user, Book book);
}
