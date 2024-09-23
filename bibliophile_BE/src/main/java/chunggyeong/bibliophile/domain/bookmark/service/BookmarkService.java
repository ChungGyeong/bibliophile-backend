package chunggyeong.bibliophile.domain.bookmark.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.book.service.BookServiceUtils;
import chunggyeong.bibliophile.domain.bookmark.domain.Bookmark;
import chunggyeong.bibliophile.domain.bookmark.domain.repository.BookmarkRepository;
import chunggyeong.bibliophile.domain.bookmark.exception.BookmarkNotFoundException;
import chunggyeong.bibliophile.domain.bookmark.exception.DuplicatedBookmarkException;
import chunggyeong.bibliophile.domain.bookmark.presentation.dto.request.AddBookmarkRequest;
import chunggyeong.bibliophile.domain.bookmark.presentation.dto.response.BookmarkResponse;
import chunggyeong.bibliophile.domain.user.domain.User;
import chunggyeong.bibliophile.global.utils.user.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService implements BookmarkServiceUtils{

    private final BookmarkRepository bookmarkRepository;
    private final BookServiceUtils bookServiceUtils;
    private final UserUtils userUtils;

    // 즐겨찾기 추가
    @Transactional
    public BookmarkResponse addBookmark(AddBookmarkRequest addBookmarkRequest) {
        Book book = bookServiceUtils.queryBook(addBookmarkRequest.bookId());
        User user = userUtils.getUserFromSecurityContext();

        if (existsByUserAndBook(user, book)) {
            throw DuplicatedBookmarkException.EXCEPTION;
        }

        Bookmark bookmark = Bookmark.createBookmark(user, book);

        bookmarkRepository.save(bookmark);

        return new BookmarkResponse(bookmark, book);
    }

    @Transactional
    public void deleteBookmark(Long bookmarkId) {
        Bookmark bookmark = queryBookmark(bookmarkId);
        User user = userUtils.getUserFromSecurityContext();

        bookmark.validUserIsHost(user);

        bookmarkRepository.delete(bookmark);
    }

    public List<BookmarkResponse> findBookmarks() {
        User user = userUtils.getUserFromSecurityContext();

        List<Bookmark> bookmarkList = bookmarkRepository.findAllByUser(user);

        return bookmarkList.stream()
                .map(bookmark -> new BookmarkResponse(bookmark, bookmark.getBook()))
                .collect(Collectors.toList());
    }

    @Override
    public Bookmark queryBookmark(Long bookmarkId) {
        return bookmarkRepository.findById(bookmarkId).orElseThrow(() -> BookmarkNotFoundException.EXCEPTION);
    }

    @Override
    public boolean existsByUserAndBook(User user, Book book) {
        return bookmarkRepository.existsByUserAndBook(user, book);
    }
}
