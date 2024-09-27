package chunggyeong.bibliophile.domain.bookmark.service;

import chunggyeong.bibliophile.domain.book.domain.Book;
import chunggyeong.bibliophile.domain.bookmark.domain.Bookmark;
import chunggyeong.bibliophile.domain.user.domain.User;

public interface BookmarkServiceUtils {

    Bookmark queryBookmark(Long bookmarkId);

    boolean existsByUserAndBook(User user, Book book);

    void deleteBookmarkByExist(boolean exist, User user, Book book);
}
