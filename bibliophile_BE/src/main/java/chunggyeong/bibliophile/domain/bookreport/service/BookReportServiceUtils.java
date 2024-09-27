package chunggyeong.bibliophile.domain.bookreport.service;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
import chunggyeong.bibliophile.domain.myBook.domain.MyBook;
import chunggyeong.bibliophile.domain.user.domain.User;

public interface BookReportServiceUtils {

    BookReport queryBookReport(Long bookReportId);

    void existsByMyBook(User user, MyBook myBook);
}
