package chunggyeong.bibliophile.domain.bookmark.domain.repository;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.bookmark.domain.Bookmark;
import chunggyeong.bibliophile.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByUser(User user);

    boolean existsByUserAndBook(User user, Book book);
}
