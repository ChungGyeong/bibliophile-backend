package chunggyeong.bibliophile.domain.book.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookServiceUtils {

    Page<BookResponse> findBookByTitle(String title, Pageable pageable);

    BookResponse findBookByIsbn(String isbn);

    Book findBookById(Long id);
}
