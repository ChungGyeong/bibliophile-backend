package chunggyeong.bibliophile.domain.bookreportimg.service;

import chunggyeong.bibliophile.domain.bookreport.domain.BookReport;
import chunggyeong.bibliophile.domain.bookreportimg.domain.BookReportImg;

import java.util.List;

public interface BookReportImgServiceUtils {
    List<BookReportImg> addBookReportImg(BookReport bookReport, List<String> bookReportImgUrl);
}
