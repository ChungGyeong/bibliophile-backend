package chunggyeong.bibliophile.domain.book.domain.repository;

import chunggyeong.bibliophile.domain.book.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    Page<Book> findByTitleContaining(String title, Pageable pageable);

    @Query(value = "SELECT * FROM book WHERE MATCH(title) AGAINST(?1 IN BOOLEAN MODE)", nativeQuery = true)
    Page<Book> searchByTitleUsingFullText(String title, Pageable pageable);

    Optional<Book> findFirstByTitle(String title);
}
