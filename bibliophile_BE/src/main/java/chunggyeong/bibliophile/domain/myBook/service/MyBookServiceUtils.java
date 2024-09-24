package chunggyeong.bibliophile.domain.myBook.service;

import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.user.domain.User;

public interface MyBookServiceUtils {

    MyBook queryMyBook(Long myBookId);

    void validUserIsHost(MyBook myBook, User user);
}
