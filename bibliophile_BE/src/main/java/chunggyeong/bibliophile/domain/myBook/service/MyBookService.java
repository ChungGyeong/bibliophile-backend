package chunggyeong.bibliophile.domain.myBook.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.book.service.BookServiceUtils;
import chunggyeong.bibliophile.domain.bookmark.service.BookmarkServiceUtils;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;
import chunggyeong.bibliophile.domain.myBook.domain.repository.MyBookRepository;
import chunggyeong.bibliophile.domain.myBook.exception.DuplicateMyBookException;
import chunggyeong.bibliophile.domain.myBook.exception.MyBookNotFoundException;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.AddMyBookRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.UpdateMyBookRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.request.UpdateMyBookStatusRequest;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.response.MyBookCountByKDC;
import chunggyeong.bibliophile.domain.myBook.presentation.dto.response.MyBookResponse;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyBookService implements MyBookServiceUtils{

    private final MyBookRepository myBookRepository;
    private final UserUtils userUtils;
    private final BookServiceUtils bookServiceUtils;
    private final BookmarkServiceUtils bookmarkServiceUtils;

    // 나의 책 등록
    @Transactional
    public MyBookResponse addMyBook(AddMyBookRequest addMyBookRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Book book = bookServiceUtils.queryBook(addMyBookRequest.bookId());
        MyBook myBook = MyBook.createMyBook(user, book);

        if (myBookRepository.existsByUserAndBook(user, book)) {
            throw DuplicateMyBookException.EXCEPTION;
        }

        myBookRepository.save(myBook);

        boolean isBookmarked = bookmarkServiceUtils.existsByUserAndBook(user, book);

        return new MyBookResponse(myBook, book, "0:0:0", 0, isBookmarked);
    }

    // 나의 책 단건 조회
    public MyBookResponse findMyBookByMyBookId(Long myBookId) {
        MyBook myBook = queryMyBook(myBookId);
        User user = userUtils.getUserFromSecurityContext();

        validUserIsHost(myBook, user);

        Book book = bookServiceUtils.queryBook(myBook.getBook().getId());
        boolean isBookmarked = bookmarkServiceUtils.existsByUserAndBook(user, book);
        String totalReadingTime = getTotalTimeFormatted(myBook.getTotalReadingTime());
        int readingTime = (int) Math.round((double) myBook.getReadingPage() / book.getPage() * 100);

        return new MyBookResponse(myBook, book, totalReadingTime, readingTime, isBookmarked);
    }

    // 나의 책장에서 보기
    public MyBookResponse findMyBookByBookId(Long bookId) {
        Book book = bookServiceUtils.queryBook(bookId);
        User user = userUtils.getUserFromSecurityContext();

        MyBook myBook = myBookRepository.findByUserAndBook(user, book).orElseThrow(() -> MyBookNotFoundException.EXCEPTION);

        boolean isBookmarked = bookmarkServiceUtils.existsByUserAndBook(user, book);
        String totalReadingTime = getTotalTimeFormatted(myBook.getTotalReadingTime());
        int readingTime = (int) Math.round((double) myBook.getReadingPage() / book.getPage() * 100);

        return new MyBookResponse(myBook, book, totalReadingTime, readingTime, isBookmarked);
    }

    // 나의 책 현재까지 읽은 페이지 변경
    @Transactional
    public MyBookResponse updateMyBook(UpdateMyBookRequest updateMyBookRequest) {
        MyBook myBook = queryMyBook(updateMyBookRequest.myBookId());
        User user = userUtils.getUserFromSecurityContext();

        validUserIsHost(myBook, user);

        Book book = bookServiceUtils.queryBook(myBook.getBook().getId());

        myBook.validPage(updateMyBookRequest.page(), book.getPage());
        myBook.updatePage(updateMyBookRequest.page());

        boolean isBookmarked = bookmarkServiceUtils.existsByUserAndBook(user, book);
        String totalReadingTime = getTotalTimeFormatted(myBook.getTotalReadingTime());
        int readingTime = (int) Math.round((double) myBook.getReadingPage() / book.getPage() * 100);

        return new MyBookResponse(myBook, book, totalReadingTime, readingTime, isBookmarked);
    }

    // 나의 책 삭제
    @Transactional
    public void deleteMyBook(Long myBookId) {
        MyBook myBook = queryMyBook(myBookId);
        User user = userUtils.getUserFromSecurityContext();

        validUserIsHost(myBook, user);

        myBookRepository.delete(myBook);
    }

    // 나의 책 상태 변경
    @Transactional
    public MyBookResponse updateMyBookStatus(UpdateMyBookStatusRequest updateMyBookStatusRequest) {
        MyBook myBook = queryMyBook(updateMyBookStatusRequest.myBookId());
        User user = userUtils.getUserFromSecurityContext();
        Book book = bookServiceUtils.queryBook(myBook.getBook().getId());
        boolean isBookmarked = bookmarkServiceUtils.existsByUserAndBook(user, book);
        String totalReadingTime = getTotalTimeFormatted(myBook.getTotalReadingTime());
        int readingTime = (int) Math.round((double) myBook.getReadingPage() / book.getPage() * 100);

        validUserIsHost(myBook, user);

        myBook.updateReadingStatus(updateMyBookStatusRequest.status());

        return new MyBookResponse(myBook, book, totalReadingTime, readingTime, isBookmarked);
    }


    // 나의 책 리스트 조회
    public List<MyBookResponse> findMyBooksByStatus(ReadingStatus readingStatus) {
        User user = userUtils.getUserFromSecurityContext();

        List<MyBook> myBookList = myBookRepository.findAllByUserAndReadingStatus(user, readingStatus);

        return myBookList.stream()
                .map(myBook -> {
                    Book book = bookServiceUtils.queryBook(myBook.getBook().getId());
                    boolean isBookmarked = bookmarkServiceUtils.existsByUserAndBook(user, book);
                    String totalReadingTime = getTotalTimeFormatted(myBook.getTotalReadingTime());
                    int readingTime = (int) Math.round((double) myBook.getReadingPage() / book.getPage() * 100);

                    return new MyBookResponse(myBook, book, totalReadingTime, readingTime, isBookmarked);
                })
                .collect(Collectors.toList());
    }

    // 나의 책 분야별 통계 조회
    public List<MyBookCountByKDC> findMyBooksStatistics() {
        User user = userUtils.getUserFromSecurityContext();
        List<MyBook> allByUser = myBookRepository.findAllByUser(user);

        Map<String, Long> kdcCountMap = allByUser.stream()
                .map(myBook -> myBook.getBook().getKdc())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        return kdcCountMap.entrySet().stream()
                .map(entry -> new MyBookCountByKDC(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }

    public String getTotalTimeFormatted(Duration totalReadingTime) {
        long hours = totalReadingTime.toHours();
        long minutes = totalReadingTime.toMinutesPart();
        long seconds = totalReadingTime.toSecondsPart();
        return String.format("%d:%d:%d초", hours, minutes, seconds);
    }

    @Override
    public void validUserIsHost(MyBook myBook, User user) {
        myBook.validUserIsHost(user);
    }

    @Override
    public MyBook queryMyBook(Long myBookId) {
        return myBookRepository.findById(myBookId).orElseThrow(() -> MyBookNotFoundException.EXCEPTION);
    }
}
