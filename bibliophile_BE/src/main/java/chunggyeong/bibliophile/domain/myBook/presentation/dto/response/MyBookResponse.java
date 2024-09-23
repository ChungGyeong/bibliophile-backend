package chunggyeong.bibliophile.domain.myBook.presentation.dto.response;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.myBook.domain.ReadingStatus;

import java.time.LocalDateTime;

public record MyBookResponse(
        Long myBookId,
        int totalPage,
        int readingPage,
        int readingPercent,
        String totalReadingTime,
        ReadingStatus readingStatus,
        boolean isBookmarked,
        String title,
        String thumbnail,
        String authors,
        String publisher,
        String contents,
        LocalDateTime completionReadingTime,
        LocalDateTime createdDate,
        LocalDateTime lastModifyDate
) {
    public MyBookResponse(MyBook myBook, Book book, String totalReadingTime, int readingPercent, boolean isBookmarked) {
        this(myBook.getId(), book.getPage(), myBook.getReadingPage(), readingPercent, totalReadingTime,
                myBook.getReadingStatus(), isBookmarked, book.getTitle(), book.getThumbnail(), book.getAuthors(),
                book.getPublisher(), book.getContents(), myBook.getCompletionReadingTime(), myBook.getCreatedDate(),
                myBook.getLastModifyDate());
    }
}
