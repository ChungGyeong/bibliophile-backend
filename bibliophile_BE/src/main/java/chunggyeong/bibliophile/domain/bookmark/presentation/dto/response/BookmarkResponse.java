package chunggyeong.bibliophile.domain.bookmark.presentation.dto.response;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.bookmark.domain.Bookmark;

import java.time.LocalDateTime;

public record BookmarkResponse(
    Long bookId,
    String title,
    String thumbnail,
    String authors,
    String publisher,
    LocalDateTime createdDate,
    LocalDateTime lastModifyDate
) {
    public BookmarkResponse(Bookmark bookmark, Book book) {
        this(book.getId(), book.getTitle(), book.getThumbnail(), book.getAuthors(), book.getPublisher(),
                bookmark.getCreatedDate(), bookmark.getLastModifyDate());
    }
}
