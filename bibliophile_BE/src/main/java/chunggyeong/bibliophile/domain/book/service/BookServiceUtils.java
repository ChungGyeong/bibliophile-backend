package chunggyeong.bibliophile.domain.book.service;

import chunggyeong.bibliophile.domain.book.presentation.dto.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookServiceUtils {

    public Page<BookResponse> findBookByTitle(String title, Pageable pageable);

    public Optional<BookResponse> findBookByIsbn(String isbn);

}
