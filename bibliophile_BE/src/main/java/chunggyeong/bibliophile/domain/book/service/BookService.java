package chunggyeong.bibliophile.domain.book.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.book.domain.repository.BookRepository;
import chunggyeong.bibliophile.domain.book.exception.BookNotFoundException;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.BookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService implements BookServiceUtils {

    private final BookRepository bookRepository;

    @Override
    public BookResponse findBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> BookNotFoundException.EXCEPTION);

        return new BookResponse(book);
    }

    @Override
    public Page<BookResponse> findBookByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContaining(title, pageable)
                .map(BookResponse::new);
    }

    @Override
    public Book queryBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> BookNotFoundException.EXCEPTION);
    }
}
