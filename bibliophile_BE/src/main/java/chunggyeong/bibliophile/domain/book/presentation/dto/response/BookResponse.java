package chunggyeong.bibliophile.domain.book.presentation.dto.response;

import chunggyeong.bibliophile.domain.book.domain.Book;

public record BookResponse(
        Long bookId,
        String contents,
        String isbn,
        String kdc,
        String title,
        String authors,
        int pageNumber,
        String thumbnail,
        String publisher,
        boolean isBookmarked
) {
    public BookResponse(Book book, boolean isBookmarked){
        this(book.getId(), book.getContents(), book.getIsbn(), book.getKdc(),
                book.getTitle(), book.getAuthors(), book.getPage(),
                book.getThumbnail(), book.getPublisher(), isBookmarked);
    }
}
