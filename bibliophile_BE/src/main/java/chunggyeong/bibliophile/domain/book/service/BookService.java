package chunggyeong.bibliophile.domain.book.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.book.domain.repository.BookRepository;
import chunggyeong.bibliophile.domain.book.exception.BookNotFoundException;
import chunggyeong.bibliophile.domain.book.presentation.dto.response.BookResponse;
import chunggyeong.bibliophile.domain.myBook.domain.repository.MyBookRepository;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService implements BookServiceUtils {

    private final BookRepository bookRepository;
    private final MyBookRepository myBookRepository;
    private final UserUtils userUtils;

    @Override
    public BookResponse findBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> BookNotFoundException.EXCEPTION);

        return new BookResponse(book);
    }

    @Override
    public Page<BookResponse> findBooksByTitle(String title, Pageable pageable) {
        return bookRepository.searchByTitleUsingFullText(title, pageable)
                .map(BookResponse::new);
    }

    public BookResponse findBookByBookId(Long bookId) {
        Book book = queryBook(bookId);
        return new BookResponse(book);
    }

    @Override
    public Book queryBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> BookNotFoundException.EXCEPTION);
    }

    @Override
    public Page<BookResponse> findPopularBooksByAgeAndGender(Pageable pageable){
        User user=userUtils.getUserFromSecurityContext();
        int ageGroup = Period.between(user.getBirthday(), LocalDate.now()).getYears()/10;
        return myBookRepository.findAllByAgeAndGenderOrderByMyBookCountDesc(ageGroup, user.getGender(), pageable)
                .map(myBook -> new BookResponse(myBook.getBook()));
    }
}
