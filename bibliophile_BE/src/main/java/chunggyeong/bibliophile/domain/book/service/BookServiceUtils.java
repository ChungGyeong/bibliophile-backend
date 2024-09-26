package chunggyeong.bibliophile.domain.book.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookServiceUtils {

    Page<BookResponse> findBooksByTitle(String title, Pageable pageable);

    BookResponse findBookByIsbn(String isbn);

    Book queryBook(Long id);

    Page<BookResponse> findPopularBooksByAgeAndGender(Pageable pageable);
}
