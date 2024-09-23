package chunggyeong.bibliophile.domain.book.presentation.dto.response;

import chunggyeong.bibliophile.domain.book.domain.Book;

public record BookResponse(
        Long bookId,
        String contents,
        String isbn,
        String kdc,
        String titleNum,
        String authors,
        int pageNumber,
        String thumbnail
) {
    public BookResponse(Book book){
        this(book.getId(), book.getContents(), book.getIsbn(), book.getKdc(),
                book.getTitle(), book.getAuthors(), book.getPage(), book.getThumbnail());
    }
}
